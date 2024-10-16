package com.example.intent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    // Hàm onCreate được gọi khi Activity khởi tạo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Kích hoạt chế độ hiển thị cạnh trên cạnh dưới cho giao diện
        setContentView(R.layout.next) // Liên kết giao diện XML với Activity

        // Tham chiếu đến các View trong layout
        val nameTextView = findViewById<TextView>(R.id.nameTextView) // Tham chiếu đến TextView hiển thị tên
        val classTextView = findViewById<TextView>(R.id.classTextView) // Tham chiếu đến TextView hiển thị lớp học
        val statusTextView = findViewById<TextView>(R.id.statusTextView) // Tham chiếu đến TextView hiển thị trạng thái tham gia
        val backButton = findViewById<Button>(R.id.backButton) // Tham chiếu đến nút Back

        // Nhận dữ liệu được truyền từ Intent của MainActivity
        val name = intent.getStringExtra("name") // Lấy dữ liệu tên từ Intent
        val className = intent.getStringExtra("className") // Lấy dữ liệu lớp học từ Intent
        val isJoined = intent.getStringExtra("isJoined") // Lấy dữ liệu trạng thái tham gia từ Intent

        // Hiển thị các dữ liệu trên TextView
        nameTextView.text = name // Gán tên cho TextView
        classTextView.text = className // Gán lớp học cho TextView
        statusTextView.text = isJoined // Gán trạng thái tham gia cho TextView

        // Thiết lập sự kiện khi nhấn nút Back
        backButton.setOnClickListener {
            finish() // Đóng Activity và quay lại Activity trước đó
        }
    }
}
