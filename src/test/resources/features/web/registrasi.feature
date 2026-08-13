Feature: Registrasi Web

  Scenario: Registrasi berhasil
    Given user berada di halaman registrasi
    When user melakukan registrasi dengan data yang valid
    Then registrasi berhasil dan pesan sukses ditampilkan
