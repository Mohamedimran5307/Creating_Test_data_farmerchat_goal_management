@echo off
REM Update User Profile API Testing - Test Runner Script (Windows)

echo 🚀 Starting Update User Profile API Tests...
echo ==============================================

REM Clean and compile
echo 📦 Cleaning and compiling project...
call mvn clean compile test-compile

if %errorlevel% neq 0 (
    echo ❌ Compilation failed!
    exit /b 1
)

echo ✅ Compilation successful!
echo.

REM Run tests
echo 🧪 Running E2E Integration Tests...
echo ====================================

call mvn test -Dcucumber.filter.tags="@E2E_Integration"

REM Check test results
if %errorlevel% equ 0 (
    echo.
    echo 🎉 All tests passed successfully!
    echo 📊 Check reports in:
    echo    - HTML: target\cucumber-html-reports\index.html
    echo    - JSON: target\jsonReports\cucumber-report.json
    echo    - Logs: Logging.txt
) else (
    echo.
    echo ❌ Some tests failed!
    echo 📋 Check logs in Logging.txt for details
    exit /b 1
)

echo.
echo 🏁 Test execution completed!
pause

