import { ThemeProvider } from "@mui/material";
import { darkTheme } from "./themes/darkTheme";

function App() {
  return (

      <ThemeProvider theme={darkTheme}>
        Code With Zosh
      </ThemeProvider>
  );
}

export default App;
