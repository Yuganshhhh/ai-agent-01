# AI Agent Tools & Workflow Demo

A Spring Boot + Spring AI project that demonstrates **tool calling**, **AI agents**, and an autonomous **website generation agent**.

## Features

- Calculator Tool
- Weather Tool (live API)
- Currency Exchange Tool
- File System Tools (create, write, read, list)
- Streaming Chat endpoint
- AI Website Generator that creates complete HTML + CSS + JS sites

## Tech Stack

- Java 21
- Spring Boot 4.1
- Spring AI (OpenAI-compatible client, used with Google Gemini)
- Maven
- Lombok

## How It Works

**Tool calling:** the AI model cannot do maths, check live weather or read exchange rates reliably on its own. Each tool is a normal Java method annotated with `@Tool`. Spring AI describes these methods to the model, the model decides when to call one and with which arguments, and Spring runs the method and sends the result back.

**Agent:** the website generator is given only file tools and a detailed system prompt. It plans the site, creates a folder, writes `index.html`, `style.css` and `script.js`, reads the files back to check them, and fixes mistakes, all by calling tools in a loop until the job is done.

```
Client --> Controller --> Service --> ChatClient (Spring AI) --> Gemini model
                                          |
                                          +--> Tools (Calculator, Weather, Currency, FileTools)
```

## Project Structure

```
src/main/java/com/example
├── DemoApplication.java            # Application entry point
├── aitool/
│   ├── CalculatorTool.java         # add, subtract, multiply, divide, mod, power
│   ├── WeatherTool.java            # Current weather via WeatherAPI
│   ├── CurrencyExchangeTool.java   # Exchange rates via Frankfurter API
│   └── FileTools.java              # Sandboxed file tools for the website agent
├── controller/
│   ├── ChatController.java         # POST /api/chat (streaming)
│   └── GenerateWebsiteController.java  # POST /api2/generate
├── service/
│   ├── ChatService.java            # Chat with tools and conversation memory
│   └── GenerateWebsiteService.java # Website agent and its system prompt
└── dto/
    └── MessageDto.java             # Request body: { "message": "..." }
```

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/chat` | Chat with the AI. The reply is streamed (Server-Sent Events). The AI can use the calculator, weather and currency tools. |
| `POST` | `/api2/generate` | Ask the agent to build or modify a website. Files are saved in `generated-sites/`. |

Both endpoints expect this JSON body:

```json
{ "message": "your text here" }
```

### Examples

Chat (use `-N` so curl shows the streamed reply):

```bash
curl -N -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "What is 25 multiplied by 48, and what is the weather in Delhi?"}'
```

Generate a website:

```bash
curl -X POST http://localhost:8080/api2/generate \
  -H "Content-Type: application/json" \
  -d '{"message": "Build a landing page for a coffee shop called Bean There"}'
```

Follow-up requests keep the context, so you can send "Make the header dark brown" afterwards and the agent will edit the existing site.

## Quick Start

1. Clone the repository
2. Copy `.env.example` to `.env` and add your API keys
3. Make the variables available to the app. Spring Boot does not read `.env` by itself, so export them in your terminal or add them to your IDE run configuration:

| Variable | Description |
| :--- | :--- |
| `OPENAI_API_KEY` | Gemini API key (required) |
| `WEATHER_API_KEY` | WeatherAPI key (required) |
| `OPENAI_BASE_URL` | Optional, defaults to Gemini's OpenAI-compatible endpoint |
| `OPENAI_MODEL` | Optional, defaults to `gemini-flash-lite-latest` |

4. Run the project:

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`.

## Generated Sites

Websites created by the agent are saved under `generated-sites/<project-name>/`. Open the `index.html` file in your browser to view them. An example (`blinkit-clone`) is included.

## Safety Notes

- The file tools can only read and write inside the `generated-sites` folder. Paths that try to escape it (like `../`) are rejected.
- The agent cannot delete files or run commands.
- Never commit your real `.env` file. It is already listed in `.gitignore`.
