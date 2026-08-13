Feature: Login Web

  Scenario: Login berhasil
    Given user berada di halaman login
    When user login dengan kredensial yang valid
    Then user berhasil masuk ke dashboard
