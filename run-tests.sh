#!/bin/bash

# Update User Profile API Testing - Test Runner Script

echo "🚀 Starting Update User Profile API Tests..."
echo "=============================================="

# Clean and compile
echo "📦 Cleaning and compiling project..."
mvn clean compile test-compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation successful!"
echo ""

# Run tests
echo "🧪 Running E2E Integration Tests..."
echo "===================================="

mvn test -Dcucumber.filter.tags="@E2E_Integration"

# Check test results
if [ $? -eq 0 ]; then
    echo ""
    echo "🎉 All tests passed successfully!"
    echo "📊 Check reports in:"
    echo "   - HTML: target/cucumber-html-reports/index.html"
    echo "   - JSON: target/jsonReports/cucumber-report.json"
    echo "   - Logs: Logging.txt"
else
    echo ""
    echo "❌ Some tests failed!"
    echo "📋 Check logs in Logging.txt for details"
    exit 1
fi

echo ""
echo "🏁 Test execution completed!"

