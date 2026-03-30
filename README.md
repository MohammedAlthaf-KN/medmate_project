# 🏥 MedMate – AI Medical Consultation App

MedMate is an AI-based Android application designed to assist users with basic medical consultation, appointment booking, and healthcare management. The app aims to make healthcare interactions simple, fast, and accessible.

---

## 🚀 Features

- 🤖 AI-based prediction of medical condition
- 🩺 Doctor appointment booking
- 📹 Video consultation with doctors
- 📄 Prescription management
- 💡 Healthcare tips and guidance
- 🔐 User authentication system (Login/Register)
- 🧑‍💼 Admin panel for managing users and doctors

---

## 🧠 AI Module

The AI consultation feature works by analyzing user-input symptoms and providing possible suggestions or health tips.

- Accepts user symptoms as input  
- Processes input using rule-based logic / basic NLP  
- Suggests possible conditions and recommendations  

> ⚠️ Note: This system is for educational purposes and does not replace professional medical advice.

---

## 🛠️ Technologies Used

- 📱 Android (Java)
- 🌐 PHP (Backend)
- 🗄️ MySQL Database
- 🔗 REST API

---

## 📂 Project Structure
Admin_medmate       → Admin Panel
medmate_android     → Android Application
medmate_php         → Backend APIs
database            → SQL Database Files

---

## ⚙️ Installation & Setup

### 📱 Android App
1. Open the project in **Android Studio**
2. Sync Gradle files
3. Connect emulator or physical device
4. Run the application

### 🌐 Backend (PHP)
1. Move `medmate_php` folder to:
C:\xampp\htdocs\

2. Start **Apache** and **MySQL** in XAMPP
3. Ensure server is running properly

### 🗄️ Database Setup
1. Open **phpMyAdmin**
2. Create a database named:
medmate

3. Import the SQL file from `/database` folder

---

## 🔗 API Endpoints (Sample)

| Endpoint | Method | Description |
|---------|--------|------------|
| /login.php | POST | User login |
| /register.php | POST | User registration |
| /book_appointment.php | POST | Book appointment |
| /get_doctors.php | GET | Fetch doctor list |

---

## 🔐 Security Features

- Password encryption implemented  
- Input validation on APIs  
- Basic protection against SQL injection  

---

## 📸 App Screenshots

### Login Screen
![Login](screenshots/login.png)

### Home Screen
![Home](screenshots/home.png)

### Doctor List
![Doctor List](screenshots/doctor_list.png)

### Appointment Booking
![Booking](screenshots/booking.png)

### Admin Panel
![Admin](screenshots/admin_dashboard.png)

---

## 🏗️ System Architecture
Android App (Java) ↓ API Calls ↓ PHP Backend ↓ MySQL Database

---

## 🔮 Future Enhancements

- 🤖 Advanced AI chatbot integration  
- 💳 Online payment integration  
- 🌍 Multi-language support  
- 📊 Health analytics dashboard  

---

## ⚠️ Disclaimer

This application is developed for educational purposes only. It does not provide real medical advice, diagnosis, or treatment. Always consult a qualified healthcare professional.

---

## 👨‍💻 Author

**Mohammed Althaf K N**

---

## ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub!
