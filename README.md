# Stevan-Kosijer-LOTR-SDK

Repository for Lotr API SDK implementation

## Getting started

### Add dependency to your project:

- for Maven users:
    - ```
      <dependency>
        <groupId>io.github.skosijer</groupId>
        <artifactId>lotr</artifactId>
        <version>1.0.0</version>
      </dependency>
      ```
- for Gradle users:
    - ```
        implementation 'io.github.skosijer:lotr:1.0.0'
      ```

## Register for Lotr API token:

[Sign up](https://the-one-api.dev/sign-up) in order to obtain an API token!
The API token is needed in order to perform requests on secure endpoints.

## How to use the SDK:

```
import io.github.skosijer.lotr.LotrClient;

var lotrClient = new LotrClient("YOUR_API_TOKEN");
```

Congratulations!
Your <code>LotrClient</code> is now configured and ready for usage.

### Basic endpoints

Entities that are supported for the current version are

* Books
* Movies
* Characters
* Chapters
* Quotes

All entities support getting by id or list.
The list APIs supports pagination, sorting and filtering (visit the [examples](#examples)).
All methods are async and non-blocking, they return <code>CompletableFuture</code>.
Feel fee to use it in any manner that suits you.

### Additional endpoints

The following additional methods are supported by the SDK.

* Chapters by book - `bookChapters(String bookId, Query query)`
* Quotes by movie - `movieQuotes(String movieId, Query query)`
* Quotes by character - `characterQuotes(String characterId, Query query)`

Mind that the `query` parameter is not mandatory, so feel free to pass `null` if you don't need the
query.

## Mind the rate limit

The Lotr API enforces a rate limit of 100 requests per 10 min so please use with caution!

## Query

Here is a completely populated query object, feel free to use it and explore the Lotr API even more:

```
        Query.builder()
            .pagination(Pagination.builder()
                .page(2)
                .limit(10)
                .offset(3)
                .build())
            .sort(Sort.builder()
                .field("fieldName")
                .sortOrder(SortOrder.ASC)
                .build())
            .filters(List.of(
                Filter.builder()
                    .field("fieldName")
                    .filterType(FilterType.MATCH)
                    .value("someValue")
                    .build()
            ))
            .build();
```

Possible `FilterType` operations are:

* `MATCH` - field is an exact match
* `NOT_MATCH` - field is not a match
* `INCLUDE` - field includes at least one value
* `NOT_INCLUDE` - field does not include the value
* `MATCH_REGEX` - matches Regular Expression
* `NOT_MATCH_REGEX` - does not match Regular Expression
* `EXIST` - field exists
* `NOT_EXIST` - field does not exist
* `GREATER_THAN` - field is greater than
* `LESSER_THAN` - field is lesser than
* `EQUALS` - field is equal

## Examples

Get book by book id.
Important note: `CompletableFuture<BookPage>` is always returned.
To await the `BookPage` use `.get()` method.

```
lotrClient.book("5cf5805fb53e011a64671582");
```

Get All books:

```
lotrClient.books();
```

Get paginated, sorted or filtered books:

```
lotrClient.books(Query.builder()
        .filters(List.of(Filter.builder()
            .field("_id")
            .filterType(FilterType.MATCH)
            .value("5cf58077b53e011a64671583")
            .build()))
        .build());
```