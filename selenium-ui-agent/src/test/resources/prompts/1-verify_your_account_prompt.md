**Main instructions:**
- Read system_prompt.md carefully, there you have the basic instructions on how to operate and using the MCP tools to navigate to webpages.

**Project context**
- Framework already set up (POM + Hooks + Driver). Do **not** edit core/config classes.
- Base URL is configurable; default is `https://qaplayground.dev`.

**Feature to implement**: features/VerifyYourAccount.feature

**Mini-app details and tips**
- The task is: "Enter valid code by pressing the key-up button or typing number and assert success message"
- Page URL: https://qaplayground.dev/apps/verify-account/
- The code is presented in the page with the text "The confirmation code is X-X-X-X-X-X"
- You have 6 squares (inputs) where you can act.
- You can either type the numbers (no need to hit enter or click a button afterwards) or use the arrows on each number  (use typing preferably).
- After tpying the correct code the page presents a container with the "Success" text (the number boxes disappear). You should assert that message.