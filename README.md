# 📚 Hệ Thống Quản Lý Thư Viện - Backend

## 📖 Giới thiệu

Hệ Thống Quản Lý Thư Viện là một ứng dụng Backend được xây dựng bằng **Spring Boot**, cung cấp các API RESTful phục vụ cho việc quản lý thư viện. Hệ thống hỗ trợ quản lý sách, danh mục sách, người dùng, xác thực và phân quyền bằng JWT, cùng với các chức năng quản lý mượn trả sách.

Dự án được phát triển nhằm mục đích học tập, nghiên cứu và thực hành các công nghệ Backend hiện đại như **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA** và **PostgreSQL**. Đồng thời, đây cũng là nền tảng để phát triển một hệ thống quản lý thư viện hoàn chỉnh theo mô hình Fullstack trong tương lai.

---

## 🎯 Mục tiêu dự án

- Xây dựng hệ thống Backend theo kiến trúc phân tầng (Layered Architecture).
- Thực hành thiết kế và phát triển RESTful API.
- Tìm hiểu cơ chế xác thực và phân quyền bằng JWT.
- Làm việc với cơ sở dữ liệu PostgreSQL thông qua JPA/Hibernate.
- Áp dụng các nguyên tắc Clean Code và Separation of Concerns.
- Nâng cao kỹ năng phát triển ứng dụng Java Spring Boot.

---

## ✨ Chức năng chính

### 🔐 Xác thực và phân quyền

- Đăng ký tài khoản.
- Đăng nhập hệ thống.
- Cấp phát Access Token và Refresh Token.
- Làm mới Access Token bằng Refresh Token.
- Đăng xuất hệ thống.
- Phân quyền người dùng theo vai trò (Role).

### 📚 Quản lý sách

- Thêm sách mới.
- Cập nhật thông tin sách.
- Xóa sách.
- Xem danh sách sách.
- Xem chi tiết sách.
- Tìm kiếm sách theo tiêu đề.
- Quản lý số lượng sách trong kho.

### 🗂 Quản lý danh mục

- Thêm danh mục sách.
- Cập nhật danh mục.
- Xóa danh mục.
- Xem danh sách danh mục.
- Liên kết sách với danh mục tương ứng.

### 👤 Quản lý người dùng

- Quản lý thông tin tài khoản.
- Theo dõi vai trò người dùng.
- Kiểm soát quyền truy cập hệ thống.

### 📖 Quản lý mượn trả sách

- Tạo phiếu mượn sách.
- Theo dõi ngày mượn và ngày trả.
- Quản lý trạng thái mượn trả.
- Lưu trữ lịch sử giao dịch mượn sách.

---

## 🏗 Kiến trúc hệ thống

Hệ thống được xây dựng theo mô hình phân tầng:

```text
Controller Layer
       │
       ▼
Service Layer
       │
       ▼
Repository Layer
       │
       ▼
PostgreSQL Database
```

### Các thành phần chính

#### Controller Layer

Tiếp nhận và xử lý các yêu cầu HTTP từ phía Client.

#### Service Layer

Chứa các nghiệp vụ và quy tắc xử lý của hệ thống.

#### Repository Layer

Thực hiện các thao tác truy xuất dữ liệu thông qua Spring Data JPA.

#### Entity Layer

Đại diện cho các bảng dữ liệu trong cơ sở dữ liệu.

#### DTO Layer

Trao đổi dữ liệu giữa Client và Server, hạn chế truy cập trực tiếp vào Entity.

#### Security Layer

Xử lý xác thực và phân quyền bằng Spring Security và JWT.

---

## 🛠 Công nghệ sử dụng

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

### Authentication & Authorization

- JWT (JSON Web Token)
- Refresh Token

### Database

- PostgreSQL

### Validation

- Jakarta Validation

### Build Tool

- Maven

### Testing

- Postman

---

## 📂 Cấu trúc dự án

```text
src/main/java
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── exception
└── util

src/main/resources
│
├── application.yml
└── static
```

---

## 🚀 Điểm nổi bật

- Xác thực người dùng bằng JWT.
- Cơ chế Refresh Token tăng cường bảo mật.
- Thiết kế API theo chuẩn RESTful.
- Phân quyền người dùng theo vai trò.
- Tách biệt rõ ràng giữa các tầng của hệ thống.
- Sử dụng DTO để kiểm soát dữ liệu trao đổi.
- Dễ dàng tích hợp với các ứng dụng Frontend như Angular hoặc React.
- Có khả năng mở rộng để bổ sung các tính năng nâng cao trong tương lai.

---

## 🔮 Hướng phát triển

- Hệ thống gợi ý sách bằng AI.
- Thống kê và báo cáo nâng cao.
- Quản lý tiền phạt khi trả sách quá hạn.
- Tích hợp gửi email thông báo.
- Tích hợp mã QR cho sách.

---