# MFD Automation Testing

Automation testing framework menggunakan **Java, Selenium WebDriver, TestNG, dan Maven** untuk melakukan pengujian otomatis pada aplikasi web.

## 🛠️ Teknologi yang Digunakan

* Java
* Selenium WebDriver
* TestNG
* Maven
* Git & GitHub
* Page Object Model (POM)

## 📁 Struktur Project

```text
mfdautomation/
├── .vscode/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── id/co/juaracoding/util/
│   │           └── TextUtil.java
│   └── test/
│       └── java/
│           └── id/co/juaracoding/
│               ├── selenium/
│               │   ├── BaseSeleniumTest.java
│               │   ├── LoginTest.java
│               │   ├── LupaPasswordTest.java
│               │   ├── RegistrasiTest.java
│               │   └── pages/
│               │       ├── LoginPage.java
│               │       ├── RegisterPage.java
│               │       └── ForgotPasswordPage.java
│               ├── testng/
│               │   └── TextUtilTest.java
│               └── util/
│                   └── TestConfig.java
├── pom.xml
├── testng.xml
└── .gitignore
```

## ⚙️ Konfigurasi

URL aplikasi dapat diatur melalui system property:

```bash
mvn clean test -Dbase.url=http://localhost:8080
```

Username dan password valid juga dapat dikonfigurasi melalui system property:

```bash
mvn clean test \
-Dbase.url=http://localhost:8080 \
-Dvalid.username=testuser \
-Dvalid.password=Test@12345
```

Jika parameter tidak diberikan, aplikasi akan menggunakan nilai default yang terdapat pada `TestConfig`.

## ▶️ Menjalankan Automation Test

Pastikan aplikasi yang akan diuji sudah berjalan terlebih dahulu.

Kemudian jalankan:

```bash
mvn clean test
```

Atau dengan konfigurasi URL tertentu:

```bash
mvn clean test -Dbase.url=http://localhost:8080
```

## 🧪 Test Case

Automation test mencakup beberapa fungsi utama:

* Login
* Login dengan data tidak valid
* Registrasi
* Lupa Password
* Utility/Text validation

Pengujian dijalankan menggunakan **TestNG** dan Selenium WebDriver.

## 🏗️ Design Pattern

Project menggunakan **Page Object Model (POM)** untuk memisahkan:

* Test case
* Page object
* Konfigurasi
* Utility

Pendekatan ini membuat kode automation lebih mudah dipelihara dan dikembangkan.

## 👨‍💻 Author

**Faisal Dzikri**

Project ini dibuat sebagai bagian dari pembelajaran dan pengembangan kemampuan **Software Quality Assurance & Automation Testing**.
