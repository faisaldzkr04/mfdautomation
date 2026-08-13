Feature: Lupa Password Web

  Scenario: Lupa password berhasil
    Given user berada di halaman lupa password
    When user meminta reset password dengan email terdaftar
    Then pesan sukses reset password ditampilkan
