# Solution Design

## Problem Description & Requirements

- An SDK for the Lotr API should be designed so that it's easy to consume and use.
- Equaly important points are paying attention to code quality, readability and future
  maintainability.
- The code should be simple to understand and well documented.
- The SDK design should be abstract and easily extensible.
- The SDK should be deployed to a public package manager repository (e.g. Maven Central).
- The SDK should include clear how-to instructions with examples.

## Proposed Design

### HttpClient

We will use HttpClient with asynchronous API in order to achieve best performance by leveraging the
non-blocking API model.
The HttpClient can also offer many other features out of the box, like using HTTP/2 protocol,
caching or limiting the request timeout. This makes it a great candidate for future extensibility.

### LotrClient

LotrClient includes all the publicly exposed methods and serves as a single point of controlling the
API presented to the clients.

### GetAllClient / GetByIdClient / GetAllByIdClient

Generic clients that can return any response data type while covering all the possible use cases set
by the Lotr API definition.

Other than being agnostic to response types they also provide flexibility for combining and mixing
certain API features.

For example:

- Books API includes the following endpoints:
    - <code>/book</code>
    - <code>/book/{id}</code>
    - <code>/book/{id}/chapter</code>
- While the Chapter API only includes:
    - <code>/chapter</code>
    - <code>/chapter/{id}</code>

So the clients proposed above provide a way of easily defining a new client or updating existing
ones for all endpoints or using them to mix and match any combination of included endpoints in the
future.

### BookService / MovieService / CharacterService / QuoteService / ChapterService

Service layer used for combining before mentioned clients in order to provide the needed grouping of
APIs available per resource.
Services are used for defining the wanted response types as well as resource names.

### QueryUtil

Provides all the necessary logic for building query parameters for the following classes

- <code>Pagination (limit, page, offset)</code>
- <code>Sort (field, sortOrder)</code>
- <code>Filter (field, filterType, value)</code>

## Testing

- Every test case code is physically separated by an empty line in order to provide the AAA
  structure (Arrange-Act-Assert).
- The test names follow the <code>should-{have-expected-result}_When-{something-is-done}</code>
  making it easy to understand them
- We currently have integration tests for fetching the actual data from the API covering most of the
  usual and specific cases
- Tests should be extended in the future with more unit and integration tests to increase test
  coverage

## Future improvements

- Thoroughly test the API and provide defensive programming where needed to ensure maximum stability
  of the SDK
- Apply better exception handling
- Apply more robust input validations (for filters and other inputs)
- Extract all the possible field names as enum values for `Sort` and `Filter` field values
- Apply performance tests and stress test the SDK to discover any possible leaks
- Increase test coverage (unit and integration tests)
- Revise and extend class/method documentation
- Collect feedback from the end users about the usability of the current SDK exposed methods
- Add automatically generated CHANGELOG.md file with semantic versioning following git
  commits (https://mokkapps.de/blog/how-to-automatically-generate-a-helpful-changelog-from-your-git-commit-messages/)
- Check how to resolve this vulnerability https://sbom.lift.sonatype.com/report/T1-a0368c8f29fdaa555824-40f18103e5d6-1670813689-a5da9c2d31054ab9a0d098b51e170c0a