package com.example.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

// Lớp MainActivity kế thừa từ AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Khai báo các biến thành phần cho các View và DataHelper
    private lateinit var dataHelper: DataHelper // Biến để tương tác với cơ sở dữ liệu (SQLite)
    private lateinit var nameEditText: EditText // Biến để nhập tên sinh viên
    private lateinit var classEditText: EditText // Biến để nhập lớp học của sinh viên
    private lateinit var radioGroup: RadioGroup // Biến để chọn trạng thái tham gia (tham gia hay chưa tham gia)
    private lateinit var okButton: Button // Biến đại diện cho nút OK
    private lateinit var exitButton: Button // Biến đại diện cho nút EXIT

    // Hàm onCreate được gọi khi Activity khởi tạo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Kích hoạt chế độ hiển thị cạnh trên cạnh dưới cho giao diện (tăng trải nghiệm hiển thị toàn màn hình)
        setContentView(R.layout.main) // Gán giao diện XML (layout) cho Activity

        // Khởi tạo đối tượng DataHelper để làm việc với cơ sở dữ liệu
        dataHelper = DataHelper(this) // Sử dụng context của Activity hiện tại để tạo một instance của DataHelper

        // Tham chiếu đến các view trong layout
        nameEditText = findViewById(R.id.nameEditText) // Lấy đối tượng EditText từ layout (nhập tên sinh viên)
        classEditText = findViewById(R.id.classEditText) // Lấy đối tượng EditText từ layout (nhập lớp học)
        radioGroup = findViewById(R.id.radioGroup) // Lấy đối tượng RadioGroup từ layout (lựa chọn tham gia hay không)
        okButton = findViewById(R.id.okButton) // Lấy đối tượng Button cho nút OK
        exitButton = findViewById(R.id.exitButton) // Lấy đối tượng Button cho nút EXIT

        // Thiết lập sự kiện khi nhấn nút OK
        okButton.setOnClickListener {
            // Lấy dữ liệu từ EditText nhập tên và lớp học
            val name = nameEditText.text.toString() // Lấy tên từ EditText
            val className = classEditText.text.toString() // Lấy lớp học từ EditText

            // Kiểm tra trạng thái RadioButton được chọn và gán chuỗi tương ứng
            val isJoined = if (radioGroup.checkedRadioButtonId == R.id.radioYes) {
                "Đã tham gia" // Nếu chọn "Yes" thì gán "Đã tham gia"
            } else {
                "Chưa tham gia" // Nếu chọn "No" thì gán "Chưa tham gia"
            }

            // Kiểm tra nếu tên và lớp học không rỗng
            if (name.isNotEmpty() && className.isNotEmpty()) {
                // Tạo một đối tượng Student mới với thông tin nhập vào
                val newStudent = Student(0, name, className, isJoined)
                // Thêm sinh viên vào cơ sở dữ liệu
                dataHelper.addStudent(newStudent)
                // Xóa dữ liệu trong các EditText sau khi thêm
                clear()
                Toast.makeText(this, "Thêm thành công.", Toast.LENGTH_SHORT).show() // Hiển thị thông báo thành công
                // Tạo Intent để chuyển sang Activity khác (MainActivity2), kèm theo dữ liệu đã nhập
                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra("name", name) // Gửi tên sang Activity mới
                    putExtra("className", className) // Gửi lớp học sang Activity mới
                    putExtra("isJoined", isJoined) // Gửi trạng thái tham gia sang Activity mới
                }
                startActivity(intent) // Bắt đầu Activity mới
            } else {
                // Nếu tên hoặc lớp học bị trống, không làm gì (bạn có thể bổ sung thông báo lỗi)
            }
        }

        // Thiết lập sự kiện khi nhấn nút EXIT
        exitButton.setOnClickListener {
            finish() // Kết thúc Activity hiện tại
        }
    }

    // Hàm xóa nội dung trong các trường nhập liệu
    fun clear() {
        nameEditText.text.clear() // Xóa dữ liệu nhập vào của EditText tên
        classEditText.text.clear() // Xóa dữ liệu nhập vào của EditText lớp
        radioGroup.clearCheck() // Bỏ chọn các RadioButton trong RadioGroup
    }
}
