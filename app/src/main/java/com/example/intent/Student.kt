package com.example.intent

// Định nghĩa class dữ liệu Student
data class Student(
    val id: Int, // ID của học sinh (dạng số nguyên)
    val name: String, // Tên của học sinh (dạng chuỗi)
    val className: String, // Lớp học của học sinh (dạng chuỗi)
    val isJoined: String // Trạng thái tham gia ("Đã tham gia" hoặc "Chưa tham gia")
)
