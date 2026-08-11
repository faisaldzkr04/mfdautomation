# MFD Automation Testing

Automation testing framework menggunakan **Java, Selenium WebDriver, TestNG, REST Assured, dan Maven** untuk melakukan pengujian otomatis pada aplikasi web dan REST API backend.

## 🛠️ Teknologi yang Digunakan

* Java
* Selenium WebDriver
* TestNG
* REST Assured
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
│               ├── restassured/
│               │   ├── BaseRestAssuredTest.java
│               │   ├── LoginApiTest.java
│               │   ├── RegisterApiTest.java
│               │   ├── ForgotPasswordApiTest.java
│               │   └── util/
│               │       └── RsaHelper.java
│               ├── testng/
│               │   └── TextUtilTest.java
│               └── util/
│                   └── TestConfig.java
├── pom.xml
├── testng.xml
└── .gitignore
```

## ⚙️ Konfigurasi Web Automation

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

Jika parameter tidak diberikan, aplikasi akan menggunakan nilai default yang terdapat pada konfigurasi test.

## 🔌 REST API Automation

Project juga menyediakan automation testing untuk REST API backend menggunakan **REST Assured**.

Endpoint yang diuji:

* `POST /api/v1/login`
* `POST /api/v1/register`
* `POST /api/v1/forgot-password`

### Base URL API

REST API menggunakan base URL yang dapat dikonfigurasi melalui system property:

```bash
-Dbase.api.url=http://localhost:8080
```

Contoh:

```bash
mvn clean test \
-Dbase.api.url=http://localhost:8080
```

### X-API-KEY

Setiap request ke endpoint `/api/v1/` membutuhkan header `X-API-KEY`.

Nilainya diberikan melalui system property dan **jangan menyimpan nilai key asli di source code atau README**:

```bash
-Dx.api.key=YOUR_X_API_KEY
```

### CAPTCHA

Test API Login dan Register membutuhkan nilai CAPTCHA yang sesuai dengan backend. Nilai tersebut dapat diberikan melalui system property:

```bash
-Dcaptcha.answer=YOUR_CAPTCHA_ANSWER \
-Dcaptcha.hash=YOUR_CAPTCHA_HASH
```

### Credential Login

Credential login dapat diberikan melalui system property:

```bash
-Dvalid.username=testuser \
-Dvalid.password=Test@12345
```

### Enkripsi RSA

Field sensitif pada endpoint tertentu dienkripsi menggunakan helper `RsaHelper` dengan RSA-OAEP SHA-256.

* **Login:** hanya `password` yang dienkripsi.
* **Register:** `password`, `email`, `birth_date`, `phone_number`, `id_card_number`, dan `tax_id_number` dienkripsi.
* **Forgot Password:** `email` dikirim sebagai plaintext dan tidak dienkripsi.

Ciphertext tidak di-hardcode. Enkripsi dilakukan kembali ketika request dibuat karena OAEP menggunakan random padding.

### Menjalankan REST API Test

Pastikan backend Simple Apps sudah berjalan terlebih dahulu.

Kemudian jalankan seluruh test:

```bash
mvn clean test \
-Dbase.api.url=http://localhost:8080 \
-Dx.api.key=YOUR_X_API_KEY \
-Dcaptcha.answer=YOUR_CAPTCHA_ANSWER \
-Dcaptcha.hash=YOUR_CAPTCHA_HASH
```

Untuk menjalankan test API tertentu:

```bash
mvn -Dtest=LoginApiTest test
```

atau:

```bash
mvn -Dtest=RegisterApiTest test
mvn -Dtest=ForgotPasswordApiTest test
```

### Assertion

Test API memvalidasi **HTTP status code** dan menggunakan field `error_code` untuk skenario gagal. Assertion tidak bergantung pada teks `message` karena isi pesan dapat berubah tanpa mengubah arti error.

## ▶️ Menjalankan Automation Test

Pastikan aplikasi yang akan diuji sudah berjalan terlebih dahulu.

Untuk menjalankan seluruh suite yang terdaftar di `testng.xml`:

```bash
mvn clean test
```

Suite mencakup:

* TestNG utility test
* Selenium UI test
* REST API test

Jika hanya ingin menjalankan Selenium dan TestNG tanpa REST API, gunakan nama class test secara langsung, misalnya:

```bash
mvn -Dtest=TextUtilTest,LoginTest,LupaPasswordTest,RegistrasiTest -Dbase.url=http://localhost:8080 test
```

## 🧪 Test Case

Automation test mencakup beberapa fungsi utama:

### UI Automation

* Login
* Login dengan data tidak valid
* Registrasi
* Lupa Password
* Utility/Text validation

Pengujian UI dijalankan menggunakan **Selenium WebDriver** dan **TestNG**.

### API Automation

* Login API berhasil dan gagal
* Register API berhasil dan gagal
* Forgot Password API berhasil dan gagal

Pengujian API dijalankan secara langsung terhadap backend menggunakan **REST Assured**, tanpa melalui browser.

## 🏗️ Design Pattern

Project menggunakan **Page Object Model (POM)** untuk pengujian UI agar test case, page object, konfigurasi, dan utility tetap terpisah.

Untuk pengujian API digunakan base test khusus REST Assured dan helper RSA untuk kebutuhan enkripsi field sesuai kontrak API.

Pendekatan ini membuat kode automation lebih mudah dipelihara dan dikembangkan.

## ⚠️ Catatan Keamanan

Jangan commit nilai berikut ke repository:

* `X-API-KEY`
* Credential production
* CAPTCHA yang bersifat sensitif
* Secret atau credential layanan eksternal

Gunakan system property atau environment variable saat menjalankan test.

## 👨‍💻 Author

**Faisal Dzikri**

Project ini dibuat sebagai bagian dari pembelajaran dan pengembangan kemampuan **Software Quality Assurance & Automation Testing**.
