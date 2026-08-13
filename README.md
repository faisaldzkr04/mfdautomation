# MFD Automation Testing

Automation testing framework menggunakan **Java, Selenium WebDriver, TestNG, REST Assured, Cucumber BDD, dan Maven** untuk melakukan pengujian otomatis pada aplikasi web dan REST API backend.

## 🛠️ Teknologi yang Digunakan

* Java
* Selenium WebDriver
* TestNG
* REST Assured
* Cucumber BDD / Gherkin
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
│       ├── java/
│       │   └── id/co/juaracoding/
│       │       ├── selenium/
│       │       │   ├── BaseSeleniumTest.java
│       │       │   ├── LoginTest.java
│       │       │   ├── LupaPasswordTest.java
│       │       │   ├── RegistrasiTest.java
│       │       │   └── pages/
│       │       │       ├── LoginPage.java
│       │       │       ├── RegisterPage.java
│       │       │       └── ForgotPasswordPage.java
│       │       ├── restassured/
│       │       │   ├── BaseRestAssuredTest.java
│       │       │   ├── LoginApiTest.java
│       │       │   ├── RegisterApiTest.java
│       │       │   ├── ForgotPasswordApiTest.java
│       │       │   └── util/
│       │       │       └── RsaHelper.java
│       │       ├── cucumber/
│       │       │   ├── api/
│       │       │   │   ├── ApiSteps.java
│       │       │   │   └── CucumberApiRunner.java
│       │       │   └── web/
│       │       │       ├── WebSteps.java
│       │       │       └── CucumberWebRunner.java
│       │       ├── testng/
│       │       │   └── TextUtilTest.java
│       │       └── util/
│       │           └── TestConfig.java
│       └── resources/
│           └── features/
│               ├── api/
│               │   ├── login.feature
│               │   ├── registrasi.feature
│               │   └── lupa_password.feature
│               └── web/
│                   ├── login.feature
│                   ├── registrasi.feature
│                   └── lupa_password.feature
├── pom.xml
├── testng.xml
└── .gitignore
```

## 🥒 Cucumber BDD Automation

Praktikum keempat menambahkan **Cucumber BDD** sebagai layer behavior-driven testing di atas automation yang sudah dibuat pada praktikum sebelumnya.

Skenario ditulis menggunakan **Gherkin** dengan pola:

```gherkin
Feature: Login

  Scenario: Login berhasil
    Given user berada di halaman login
    When user memasukkan username dan password yang valid
    And user menekan tombol login
    Then user berhasil masuk ke dashboard
```

Cucumber digunakan untuk membungkus automation yang sudah ada, bukan membuat framework baru. Pada layer Web, step definition menggunakan kembali **Page Object Selenium**. Pada layer API, step definition menggunakan kembali logic **REST Assured** dan helper RSA yang telah dibuat sebelumnya.

### Feature Web

Tersedia tiga feature utama:

* `login.feature`
* `registrasi.feature`
* `lupa_password.feature`

### Feature API

Tersedia tiga feature utama:

* `login.feature`
* `registrasi.feature`
* `lupa_password.feature`

### Cucumber Runner

Project menggunakan `AbstractTestNGCucumberTests` sehingga Cucumber dapat diintegrasikan dengan TestNG melalui:

* `CucumberWebRunner.java`
* `CucumberApiRunner.java`

Runner Cucumber juga sudah didaftarkan pada `testng.xml`.

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

Project menyediakan automation testing untuk REST API backend menggunakan **REST Assured**.

Endpoint yang diuji:

* `POST /api/v1/login`
* `POST /api/v1/register`
* `POST /api/v1/forgot-password`

### Base URL API

REST API menggunakan base URL yang dapat dikonfigurasi melalui system property:

```bash
-Dbase.api.url=http://localhost:8080
```

### X-API-KEY

Setiap request ke endpoint `/api/v1/` membutuhkan header `X-API-KEY`.

```bash
-Dx.api.key=YOUR_X_API_KEY
```

Jangan menyimpan nilai key asli di source code atau README.

### CAPTCHA

Test API Login dan Register membutuhkan CAPTCHA yang sesuai dengan backend:

```bash
-Dcaptcha.answer=YOUR_CAPTCHA_ANSWER \
-Dcaptcha.hash=YOUR_CAPTCHA_HASH
```

### Enkripsi RSA

Field sensitif pada endpoint tertentu dienkripsi menggunakan helper `RsaHelper` dengan RSA-OAEP SHA-256.

* **Login:** hanya `password` yang dienkripsi.
* **Register:** `password`, `email`, `birth_date`, `phone_number`, `id_card_number`, dan `tax_id_number` dienkripsi.
* **Forgot Password:** `email` dikirim sebagai plaintext dan tidak dienkripsi.

Ciphertext tidak di-hardcode. Enkripsi dilakukan kembali ketika request dibuat karena OAEP menggunakan random padding.

## ▶️ Menjalankan Automation Test

Pastikan aplikasi/backend yang akan diuji sudah berjalan terlebih dahulu.

Untuk menjalankan seluruh suite yang terdaftar di `testng.xml`:

```bash
mvn clean test
```

Suite mencakup:

* TestNG utility test
* Selenium UI test
* REST API test
* Cucumber Web BDD test
* Cucumber API BDD test

Untuk menjalankan Cucumber secara langsung berdasarkan runner:

```bash
mvn -Dtest=CucumberWebRunner test
mvn -Dtest=CucumberApiRunner test
```

Untuk menjalankan test API tertentu:

```bash
mvn -Dtest=LoginApiTest test
mvn -Dtest=RegisterApiTest test
mvn -Dtest=ForgotPasswordApiTest test
```

## 🧪 Test Coverage

### UI Automation

* Login
* Login dengan data tidak valid
* Registrasi
* Lupa Password
* Utility/Text validation

Pengujian UI menggunakan **Selenium WebDriver**, **TestNG**, dan **Page Object Model**.

### API Automation

* Login API berhasil dan gagal
* Register API berhasil dan gagal
* Forgot Password API berhasil dan gagal

Pengujian API dijalankan langsung terhadap backend menggunakan **REST Assured**, tanpa melalui browser.

### BDD Automation

Flow Web dan API yang sama juga direpresentasikan dalam skenario **Cucumber/Gherkin**:

| Layer | Login | Registrasi | Lupa Password |
|---|---|---|---|
| Web | ✅ | ✅ | ✅ |
| API | ✅ | ✅ | ✅ |

Dengan pendekatan ini, framework mempertahankan automation Selenium dan REST Assured yang sudah ada sekaligus menyediakan skenario BDD yang lebih mudah dibaca oleh anggota tim QA maupun non-teknis.

## 🏗️ Design Pattern & Arsitektur

Project menggunakan **Page Object Model (POM)** untuk pengujian UI agar test case, page object, konfigurasi, dan utility tetap terpisah.

Untuk API digunakan base test khusus REST Assured dan helper RSA untuk kebutuhan enkripsi field sesuai kontrak API.

Untuk BDD digunakan pemisahan antara:

* **Feature:** skenario bisnis dalam Gherkin.
* **Step Definition:** implementasi langkah Cucumber.
* **Runner:** integrasi Cucumber dengan TestNG.
* **Existing Automation Layer:** Page Object Selenium dan logic REST Assured yang sudah ada.

Pendekatan ini menjaga prinsip **reuse**, sehingga penambahan Cucumber tidak menduplikasi automation logic yang sudah tersedia.

## ⚠️ Catatan Keamanan

Jangan commit nilai berikut ke repository:

* `X-API-KEY`
* Credential production
* CAPTCHA yang bersifat sensitif
* Secret atau credential layanan eksternal

Gunakan system property atau environment variable saat menjalankan test.

## 👨‍💻 Author

**Mohammad Faisal Dzikri**

Project ini dibuat sebagai bagian dari pembelajaran dan pengembangan kemampuan **Software Quality Assurance & Automation Testing**.
