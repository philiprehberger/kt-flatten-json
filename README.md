# flatten-json

[![CI](https://github.com/philiprehberger/kt-flatten-json/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-flatten-json/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/flatten-json)](https://central.sonatype.com/artifact/com.philiprehberger/flatten-json)
[![License](https://img.shields.io/github/license/philiprehberger/kt-flatten-json)](LICENSE)

Flatten nested maps to dot-notation keys and unflatten back.

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.philiprehberger:flatten-json:0.1.3")
}
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>flatten-json</artifactId>
    <version>0.1.3</version>
</dependency>
```

## Usage

```kotlin
import com.philiprehberger.flattenjson.*

val nested = mapOf("user" to mapOf("name" to "Alice", "address" to mapOf("city" to "Berlin")))
val flat = flatten(nested)
// {"user.name": "Alice", "user.address.city": "Berlin"}

val restored = unflatten(flat) // back to nested
```

## API

| Function / Class | Description |
|------------------|-------------|
| `flatten(map, separator, prefix, maxDepth)` | Flatten nested map to dot-notation keys |
| `unflatten(map, separator)` | Restore nested structure from flat map |

## Development

```bash
./gradlew test       # Run tests
./gradlew build      # Build JAR
```

## License

MIT
