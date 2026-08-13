Feature: Lupa Password API

  Scenario: Lupa password API berhasil
    Given request API lupa password siap digunakan
    When client mengirim request lupa password dengan email terdaftar
    Then response lupa password berhasil
