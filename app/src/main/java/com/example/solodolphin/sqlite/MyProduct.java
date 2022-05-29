package com.example.solodolphin.sqlite;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


import com.example.solodolphin.models.CartModel;
import com.example.solodolphin.models.DetailedDailyModel;
import com.example.solodolphin.models.HomeVerModel;


import java.util.ArrayList;
import java.util.List;

public class MyProduct extends SQLiteOpenHelper {

//    public static final String DB_NAME = "AppDatHang";
//    public static final int DB_VERSION = 1;


    //Tên bảng
    public static final String TableName = "FoodTable";
    //Tên các cột trong bảng
    public static final String Id = "ID";
    public static final String Name = "Name";
    public static final String Cate = "Category";
    public static final String Price = "Price";
    public static final String Des = "Description";
    public static final String Dis = "Discount";
    public static final String Img = "Image";

    public MyProduct(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate = "Create table if not exists " + TableName + " ("
                +Id + " Integer Primary Key AUTOINCREMENT, "
                + Name + " Text,"
                + Cate +" Text,"
                + Price + " Integer,"
                + Des + " Text,"
                + Dis + " Text,"
                + Img + " BLOB)";

        String sqlCreateUser = "Create table if not exists User (" +
                "UserId Integer Primary Key AUTOINCREMENT, " +
                "Username Text, " +
                "Gmail Text, " +
                "Password Text, "+
                "Address Text, "+
                "IsAdmin Text)";

        String sqlCreateOrder = "Create table if not exists Orders ("+
                "OrderId Integer Primary key Autoincrement, "+
                "OrderDate Date, "+
                "OrderAddress Text, "+
                "OrderState Text,"+
                "TotalCost Integer,"+
                "UserId Integer,"+
                "Foreign key (UserId) references User(UserId))";

        String sqlCreateOrderDetail = "Create table if not exists OrderDetail ("+
                "OrderDetailId Integer primary key autoincrement, "+
                "Quantity Integer ,"+
                "Cost Integer ,"+
                "OrderId Integer, "+
                "FoodId Integer, "+
                "Foreign key(OrderId) references Orders(OrderId), "+
                "Foreign key(FoodId) references FoodTable(Id))";


        db.execSQL(sqlCreate);
        db.execSQL(sqlCreateUser);
        db.execSQL(sqlCreateOrder);
        db.execSQL(sqlCreateOrderDetail);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //xóa bảng đã có
        db.execSQL("Drop table if exists "+ TableName);
        db.execSQL("Drop table if exists User");
        //tạo lại
        onCreate(db);
    }


    // Làm việc với sản phẩm

    public ArrayList<DetailedDailyModel> SelectCategoryForDaily(String daily) {
         ArrayList<DetailedDailyModel> detailList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TableName + " where "+Cate+" = '" + daily +"'";
//        String sql = "Select * from " + TableName + " where Name = '" +daily+ "'";
        //chạy câu lệnh truy vấn trả về dạng Cursor
        Cursor cursor = db.rawQuery(sql, null);
        //tạo ArrayList để trả về;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                DetailedDailyModel detail = new DetailedDailyModel(
                        cursor.getInt(0),
                        cursor.getBlob(6),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(2),
                        cursor.getInt(3));
                detailList.add(detail);
            }
        }

        db.close();
        assert cursor != null;
        cursor.close();
        return detailList;
    }

    public ArrayList<HomeVerModel> SelectCategoryByType(String type){
        ArrayList<HomeVerModel> verlist = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from " + TableName + " where "+Cate+" = '" + type +"'";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                HomeVerModel verModel = new HomeVerModel(
                        cursor.getInt(0),
                        cursor.getBlob(6),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getInt(3));
                verlist.add(verModel);
            }
        }
        db.close();
        return verlist;
    }

    public void InsertValue(String ten, String cate, int gia, byte[] hinh ){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Insert into "+ TableName+" values(null,?,?,?,null,null,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,ten);
        statement.bindString(2,cate);
        statement.bindDouble(3, gia);
        statement.bindBlob(4, hinh);

        statement.executeInsert();
        db.close();
    }

    public ArrayList<Food> getAllFood(){
        ArrayList<Food> list = new ArrayList<>();
        //câu truy vấn
        String sql = "Select * from " + TableName;
        //   String sql = "Select * from " + TableName + " where Id = 1";
        //Lấy đối tượng csdl sql lite
        SQLiteDatabase db = this.getReadableDatabase();
        //chạy câu lệnh truy vấn trả về dạng Cursor
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        //tạo ArrayList để trả về;
        if(cursor != null){
            while(cursor.moveToNext()){
                Food food = new Food(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getBlob(6));
                list.add(food);
            }
        }
        db.close();
        return list;

    }

    public boolean checkFoodInDetail(int foodId){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from OrderDetail where FoodId = " +foodId;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0)
        {
            db.close();
            cursor.close();
            return true;
        }
        else {
            db.close();
            cursor.close();
            return false;
        }
    }

    public void deleteFood(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where Id = "+ id;
        db.execSQL(sql);
        db.close();
    }

    public boolean updateFood(int id, Food food){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, food.name);
        values.put(Cate, food.cate);
        values.put(Price, food.getPrice());
        values.put(Des, food.Des);
        values.put(Dis, food.Dis);
        values.put(Img, food.getImgID());
        int result = db.update(TableName, values,  Id + "=?",
                new String[]{String.valueOf(id)});
        db.close();
        return result != -1;
    }


    //Làm việc với Order, orderDetail
    public long getNewestOrders(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteStatement st = db.compileStatement("Select last_insert_rowid() from Orders");
        return st.simpleQueryForLong();
    }

    public void MakeOrder(List<CartModel> cartModelList, User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues orderValues = new ContentValues();
        orderValues.put("UserId", user.userId);
        orderValues.put("OrderAddress", user.userAddress);
        db.insert("Orders", null, orderValues);
        long newOrderId = getNewestOrders();
        int totalCost = 0;
        for(int i = 0; i<cartModelList.size();i++)
        {
            ContentValues detailValue = new ContentValues();
            detailValue.put("Quantity", cartModelList.get(i).getQuantity());
            detailValue.put("Cost", cartModelList.get(i).getQuantity()* cartModelList.get(i).getPrice());
            detailValue.put("OrderId", newOrderId);
            detailValue.put("FoodId", cartModelList.get(i).getId());
            totalCost+= cartModelList.get(i).getQuantity()* cartModelList.get(i).getPrice();
            db.insert("OrderDetail", null, detailValue);
        }

        String updateCost = "Update Orders Set TotalCost = "+ totalCost + " Where OrderId = "+ newOrderId;
        db.execSQL(updateCost);
    }


    //Làm việc với User
    public boolean RegisterAccount( String username, String gmail, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("Gmail", gmail);
        contentValues.put("Password", password);
        long result = db.insert("User",null,contentValues);
        return result != -1;

    }

    public boolean checkUserMail(String mail){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where Gmail = ?", new String[]{mail});
        if(cursor.getCount() > 0)
        {
            db.close();
            cursor.close();
            return true;
        }
        else {
            db.close();
            cursor.close();
            return false;
        }
    }

    public boolean checkUsernameAndPassword(String gmail, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where GMail = ? and Password = ?", new String[]{gmail, password});
        if(cursor.getCount() >0) {
            db.close();
            cursor.close();
            return true;
        }
        else {
            db.close();
            cursor.close();
            return false;
        }
    }

    public User getUser(String mail, String pass){
        User my = new User();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where GMail = ? and Password = ?", new String[]{mail, pass});
        if(cursor != null){
            while(cursor.moveToNext()) {
                my = new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
            }
        }
        assert cursor != null;
        cursor.close();
        db.close();

        return my;

    }

    public boolean isAdmin(String mail){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where Gmail = ? and IsAdmin is not null", new String[]{mail});
        if(cursor.getCount() > 0)
        {
            db.close();
            cursor.close();
            return true;
        }
        else {
            db.close();
            cursor.close();
            return false;
        }
    }

    public boolean addressIsNotNull(String mail){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where Gmail = ? and Address is not null", new String[]{mail});
        if(cursor.getCount() > 0)
        {
            db.close();
            cursor.close();
            return true;
        }
        else {
            db.close();
            cursor.close();
            return false;
        }



    }

    public boolean updateUser(int userId, User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.userName);
        values.put("Gmail", user.userMail);
        values.put("Password", user.userPass);
        values.put("Address", user.userAddress);
        values.put("IsAdmin", user.isAdmin);
        int result = db.update("User", values,  "UserId" + "=?",
                new String[]{String.valueOf(userId)});

        db.close();
        return result != -1;

    }






//    public void dropTalbe(){
//       SQLiteDatabase db = getWritableDatabase();
//       String sql = "Drop table if exists "+TableName;
//       db.execSQL(sql);
//       db.close();
//    }






}
