# Update User Profile API Testing

This project is a comprehensive API testing suite for the "Update User Profile" application using Cucumber (BDD) and RestAssured.

## Features

- **Complete E2E Integration Testing**: Tests the full user journey from OTP generation to profile update
- **Dynamic Data Generation**: Generates unique phone numbers, device IDs, and coordinates for each test
- **Multiple API Endpoints**: Tests Generate OTP, Verify OTP, Update Profile, Update Location, and Verify Referral Code APIs
- **Comprehensive Logging**: All API requests and responses are logged to `Logging.txt`
- **Phone Number Range Testing**: Tests phone numbers from 9393932218 to 9393932240
- **User Summary Reports**: Prints user IDs with names for tracking

## Project Structure

```
src/
├── main/java/pojo/           # POJO classes for API requests
│   ├── GenerateOtpRequest.java
│   ├── VerifyOtpRequest.java
│   ├── UpdateUserProfile.java
│   ├── UpdateUserLocation.java
│   └── VerifyReferralCode.java
└── test/java/
    ├── cucumber/Options/     # Test runner configuration
    │   └── TestRunner.java
    ├── features/            # Cucumber feature files
    │   └── updateUserProfile.feature
    ├── resources/           # Test utilities and configuration
    │   ├── APIResources.java
    │   ├── TestDataBuild.java
    │   ├── Utilities.java
    │   └── global.properties
    └── stepDefinitions/     # Cucumber step definitions
        └── StepDefinitions.java
```

## API Endpoints Tested

1. **Generate OTP** (`/api/user/generate_otp/`)
2. **Verify OTP** (`/api/user/verify_otp/`)
3. **Update User Profile** (`/api/user/update_user_profile/`)
4. **Update User Location** (`/api/user/update_user_location/`)
5. **Verify Referral Code** (`/api/user/verify_referral_code/`)

## Test Data

- **Phone Numbers**: 9393932218 to 9393932240 (23 test cases)
- **User Names**: Predefined Indian names for each phone number
- **Referral Code**: Hardcoded as "9090202056"
- **Coordinates**: Fixed coordinates (13.165758141882232, 75.86650729179382) for location testing

## Running Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Command Line
```bash
# Run all tests
mvn test

# Run specific tag
mvn test -Dcucumber.filter.tags="@E2E_Integration"

# Run with specific profile
mvn test -Dtest=TestRunner
```

### IDE
Run the `TestRunner.java` class directly from your IDE.

## Configuration

Edit `src/test/java/resources/global.properties` to modify:
- Base URL
- Phone number ranges
- Default OTP and referral codes
- Logging settings

## Logging

All API requests and responses are logged to `Logging.txt` in the project root. The log includes:
- Request details (URL, headers, body)
- Response details (status code, headers, body)
- Timestamps for each API call

## Test Reports

After running tests, reports are generated in:
- **JSON Report**: `target/jsonReports/cucumber-report.json`
- **HTML Report**: `target/cucumber-html-reports/`

## Key Features

### Dynamic Data Generation
- **Phone Numbers**: Sequential generation within specified range
- **Device IDs**: UUID-based unique generation
- **Coordinates**: Dynamic latitude/longitude within Indian geographical bounds

### Error Handling
- Comprehensive error logging and debugging
- Flexible status code validation (accepts both 200 and 400 for referral codes)
- Detailed response validation

### User Tracking
- Extracts and tracks user IDs from API responses
- Maps phone numbers to user names
- Prints comprehensive user summary reports

## Troubleshooting

### Common Issues
1. **400 Bad Request**: Check request headers and payload structure
2. **Ambiguous Step Definitions**: Ensure unique step definition patterns
3. **Missing Dependencies**: Run `mvn clean install` to resolve dependency issues

### Debug Mode
Enable detailed logging by setting `enableDetailedLogging=true` in `global.properties`.

## Contributing

1. Follow the existing code structure and naming conventions
2. Add comprehensive logging for new API endpoints
3. Update this README when adding new features
4. Ensure all tests pass before submitting changes

## License

This project is for testing purposes only.

# Creating_Test_data_farmerchat_goal_management
