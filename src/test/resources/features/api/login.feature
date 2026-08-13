Feature: Login API

  Scenario: Login API berhasil
    Given request API login siap digunakan
    When client mengirim request login dengan kredensial valid
    Then response login berhasil
