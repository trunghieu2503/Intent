package com.example.intent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

// Lớp MainActivity2 kế thừa từ AppCompatActivity
class MainActivity2 : AppCompatActivity() {
    // Khai báo các biến thành phần cho các View
    private lateinit var nameTextView: TextView // Biến để hiển thị tên sinh viên
    private lateinit var classTextView: TextView // Biến để hiển thị lớp học của sinh viên
    private lateinit var statusTextView: TextView // Biến để hiển thị trạng thái tham gia
    private lateinit var backButton: Button // Biến đại diện cho nút Back

    // Hàm onCreate được gọi khi Activity khởi tạo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Kích hoạt chế độ hiển thị cạnh trên cạnh dưới cho giao diện
        setContentView(R.layout.next) // Gán giao diện XML (layout) cho Activity

        // Tham chiếu đến các View trong layout
        nameTextView = findViewById(R.id.nameTextView) // Lấy đối tượng TextView từ layout để hiển thị tên
        classTextView = findViewById(R.id.classTextView) // Lấy đối tượng TextView từ layout để hiển thị lớp học
        statusTextView = findViewById(R.id.statusTextView) // Lấy đối tượng TextView từ layout để hiển thị trạng thái tham gia
        backButton = findViewById(R.id.backButton) // Lấy đối tượng Button cho nút Back

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
            finish() // Kết thúc Activity và quay lại Activity trước đó
        }
    }
}
