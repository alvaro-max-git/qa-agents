# QA Agents & MCP Experiments

This repository serves as a sandbox for exploring the capabilities of **AI Agents** within the Quality Assurance workflow. 

The primary goal is to test how Large Language Models (LLMs), connected via the **Model Context Protocol (MCP)**, can autonomously navigate websites, understand DOM structures, and generate robust, self-correcting test automation code.

## Project Structure

The repository is divided into two main experiments:

1.  **`selenium-ui-agent`**: A Java + Maven + Cucumber project using the **Selenium MCP**.
2.  **`playwright-ui-agent`**: A TypeScript + Playwright project using the **Playwright MCP**.

---

## Selenium UI Agent

This experiment simulates an **Autonomous Coding Agent**. The agent is tasked with implementing missing test steps for a pre-configured framework without human intervention.

### How it works

The workflow is defined in `src/test/resources/prompts/system_prompt.md`. The agent operates in a loop:

1.  **Input**: The agent receives a Gherkin scenario (e.g., "Verify dynamic table values").
2.  **Exploration**: Using the **Selenium MCP tools**, the agent actually navigates to the target website (`https://qaplayground.dev`) to inspect the DOM and understand selectors in real-time.
3.  **Implementation**: It generates the necessary **Page Objects** and **Step Definitions** in Java.
    *   *Guardrails*: The agent is strictly forbidden from modifying core framework files (`DriverManager`, `Hooks`, etc.).
    *   *Pattern*: It must strictly follow the Page Object Model and use `PageFactory`.
4.  **Verification**: It runs the tests (`mvn test`).
5.  **Self-Correction**: If the test fails, the agent analyzes the logs, fixes the specific Page/Step code, and retries (up to 3 attempts).

### Setting up the Selenium MCP

To enable the AI Agent to "drive" the browser and inspect pages within VS Code (or Cursor), you need to configure the MCP server.

Add the following configuration to your `.vscode/mcp.json` file:

```json
{
  "servers": {
    "selenium": {
      "command": "npx",
      "args": ["-y", "@angiejones/mcp-selenium"]
    }
  }
}
