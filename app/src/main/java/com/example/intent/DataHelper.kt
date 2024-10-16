package com.example.intent

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Lớp DataHelper để quản lý cơ sở dữ liệu SQLite
class DataHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "students.db" // Tên cơ sở dữ liệu
        private const val DATABASE_VERSION = 1 // Phiên bản cơ sở dữ liệu

        // Tên bảng và các cột
        const val TABLE_NAME = "students" // Tên bảng
        const val COLUMN_ID = "id" // Cột ID, tự động tăng
        const val COLUMN_NAME = "name" // Cột tên sinh viên
        const val COLUMN_CLASS = "class" // Cột lớp học
        const val COLUMN_IS_JOINED = "is_joined" // Cột trạng thái tham gia
    }

    // Phương thức tạo bảng khi cơ sở dữ liệu được tạo lần đầu
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," + // ID tự động tăng
                "$COLUMN_NAME TEXT," + // Cột tên sinh viên
                "$COLUMN_CLASS TEXT," + // Cột lớp học
                "$COLUMN_IS_JOINED TEXT)") // Cột trạng thái tham gia
        db?.execSQL(createTable) // Thực thi câu lệnh SQL để tạo bảng
    }

    // Phương thức nâng cấp cơ sở dữ liệu, được gọi khi phiên bản DB thay đổi
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME") // Xóa bảng cũ nếu có
        onCreate(db) // Tạo lại bảng
    }

    // Phương thức thêm sinh viên mới vào cơ sở dữ liệu
    fun addStudent(name: String, className: String, isJoined: String) {
        val db = this.writableDatabase // Mở cơ sở dữ liệu để ghi
        val values = ContentValues().apply {
            put(COLUMN_NAME, name) // Gán giá trị tên sinh viên
            put(COLUMN_CLASS, className) // Gán giá trị lớp học
            put(COLUMN_IS_JOINED, isJoined) // Gán giá trị trạng thái tham gia
        }
        db.insert(TABLE_NAME, null, values) // Thêm dữ liệu vào bảng
        db.close() // Đóng cơ sở dữ liệu sau khi thêm
    }
}
