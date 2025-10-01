# Quick Start Guide

## 🚀 Getting Started

### 1. Prerequisites
- Java 11+
- Maven 3.6+
- IDE (Eclipse/IntelliJ)

### 2. Clone and Setup
```bash
cd /Users/shaikmohamedimran/eclipse-workspace/UpdateUserProfile_API_Testing
mvn clean install
```

### 3. Run Tests
```bash
# Run all E2E tests
mvn test -Dcucumber.filter.tags="@E2E_Integration"

# Or run from IDE
# Right-click TestRunner.java → Run As → JUnit Test
```

## 📋 Test Flow

1. **Generate OTP** → Phone numbers 9393932218-9393932240
2. **Verify OTP** → Extract auth token and user ID
3. **Update Profile** → Set user name and role
4. **Update Location** → Set coordinates

## 📊 Expected Results

- **23 test cases** (one per phone number)
- **All APIs return 200/201** status codes
- **Logging.txt** contains all request/response details
- **User summary** printed for each test

## 🔧 Configuration

Edit `src/test/java/resources/global.properties`:
```properties
baseUrl=https://farmerchat.farmstack.co/mobile-app-stage
```

## 📁 Key Files

- `TestRunner.java` - Main test runner
- `updateUserProfile.feature` - Test scenarios
- `StepDefinitions.java` - Test implementation
- `Logging.txt` - API request/response logs

## 🐛 Troubleshooting

**Issue**: 400 Bad Request
**Solution**: Check headers and payload structure

**Issue**: Missing dependencies
**Solution**: Run `mvn clean install`

**Issue**: Ambiguous step definitions
**Solution**: Ensure unique step patterns

## 📈 Reports

After running tests:
- HTML Report: `target/cucumber-html-reports/index.html`
- JSON Report: `target/jsonReports/cucumber-report.json`

