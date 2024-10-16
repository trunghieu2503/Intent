package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var dataHelper: DataHelper

    // Hàm onCreate được gọi khi Activity khởi tạo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Kích hoạt chế độ hiển thị cạnh trên cạnh dưới cho giao diện
        setContentView(R.layout.main) // Liên kết giao diện XML với Activity

        dataHelper = DataHelper(this) // Khởi tạo đối tượng DataHelper để làm việc với cơ sở dữ liệu

        // Tham chiếu đến các view trong layout
        val nameEditText = findViewById<EditText>(R.id.nameEditText) // Tham chiếu đến EditText nhập tên
        val classEditText = findViewById<EditText>(R.id.classEditText) // Tham chiếu đến EditText nhập lớp học
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup) // Tham chiếu đến RadioGroup chọn tham gia hay không
        val okButton = findViewById<Button>(R.id.okButton) // Tham chiếu đến nút OK
        val exitButton = findViewById<Button>(R.id.exitButton) // Tham chiếu đến nút EXIT

        // Thiết lập sự kiện khi nhấn nút OK
        okButton.setOnClickListener {
            val name = nameEditText.text.toString() // Lấy dữ liệu từ EditText nhập tên
            val className = classEditText.text.toString() // Lấy dữ liệu từ EditText nhập lớp học
            val isJoined = if (radioGroup.checkedRadioButtonId == R.id.radioYes) {
                "Đã tham gia" // Nếu chọn radioYes thì gán "Đã tham gia"
            } else {
                "Chưa tham gia" // Nếu không thì gán "Chưa tham gia"
            }

            // Kiểm tra nếu chưa nhập đủ tên và lớp học, hiện thông báo lỗi
            if (name.isEmpty() || className.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên và lớp học.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Dừng xử lý sự kiện nếu có lỗi
            }

            // Thêm thông tin học sinh vào cơ sở dữ liệu thông qua DataHelper
            dataHelper.addStudent(name, className, isJoined)

            // Tạo Intent để chuyển sang Activity MainActivity2, kèm theo dữ liệu tên, lớp và trạng thái tham gia
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", name)
            intent.putExtra("className", className)
            intent.putExtra("isJoined", isJoined)
            startActivity(intent) // Bắt đầu Activity mới
        }

        // Thiết lập sự kiện khi nhấn nút EXIT
        exitButton.setOnClickListener {
            finish() // Đóng Activity hiện tại
        }
    }
}
