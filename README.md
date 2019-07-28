# flatten-json

[![Tests](https://github.com/philiprehberger/kt-flatten-json/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-flatten-json/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/flatten-json.svg)](https://central.sonatype.com/artifact/com.philiprehberger/flatten-json)
[![Last updated](https://img.shields.io/github/last-commit/philiprehberger/kt-flatten-json)](https://github.com/philiprehberger/kt-flatten-json/commits/main)

Flatten nested maps to dot-notation keys and unflatten back.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:flatten-json:0.1.4")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>flatten-json</artifactId>
    <version>0.1.4</version>
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

## Support

If you find this project useful:

⭐ [Star the repo](https://github.com/philiprehberger/kt-flatten-json)

🐛 [Report issues](https://github.com/philiprehberger/kt-flatten-json/issues?q=is%3Aissue+is%3Aopen+label%3Abug)

💡 [Suggest features](https://github.com/philiprehberger/kt-flatten-json/issues?q=is%3Aissue+is%3Aopen+label%3Aenhancement)

❤️ [Sponsor development](https://github.com/sponsors/philiprehberger)

🌐 [All Open Source Projects](https://philiprehberger.com/open-source-packages)

💻 [GitHub Profile](https://github.com/philiprehberger)

🔗 [LinkedIn Profile](https://www.linkedin.com/in/philiprehberger)

## License

[MIT](LICENSE)
