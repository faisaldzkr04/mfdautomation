Feature: Registrasi API

  Scenario: Registrasi API berhasil
    Given request API registrasi siap digunakan
    When client mengirim request registrasi dengan data valid
    Then response registrasi berhasil
