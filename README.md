# Multithreaded Web Server in Java

A project to understand how different server architectures handle concurrent client requests.

## Implementations

- Single Threaded Server
- Thread-per-Connection Server
- Thread Pool Server (ExecutorService)

## Tech Stack

- Java
- Socket Programming
- Java Concurrency
- Apache JMeter

## Load Testing

Used Apache JMeter to simulate up to 60,000 client requests and compare performance.

## Key Learnings

- Thread creation overhead
- Concurrent request handling
- Thread pools and resource management
- TCP socket communication

## Results

| Architecture | Observation |
|-------------|-------------|
| Single Threaded | Requests queue up under load |
| Thread-per-Connection | Better concurrency but high thread creation cost |
| Thread Pool | Most stable and scalable implementation |

## Future Improvements

- HTTP Protocol Support
- Request Routing
- Static File Serving
- Performance Metrics Dashboard
