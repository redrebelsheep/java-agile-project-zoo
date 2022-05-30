import MainPage from './pages/MainPage.jsx'
import Sidebar from './components/sidebar/Sidebar.jsx'
import {Box, Stack} from '@mui/material';
import { CssBaseline } from '@mui/material';
import Navbar from './components/navbar/Navbar.jsx';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom"
import {theme}  from './components/theme/theme.js';
import { ThemeProvider } from '@mui/material';
import TestPageForAxios from "./pages/TestPageForAxios";


const App = () => {
  return (
    <Box>
      {/* <ThemeProvider theme={theme}>
      <Button variant="contained" color="secondary">Contained</Button>
      </ThemeProvider> */}
      <Navbar/>
        <ThemeProvider theme={theme}>
            <CssBaseline />
      <Stack direction="row" spacing={3} justifyContent="space-between">
      <Sidebar/>
      <Router>
          <Routes>
              <Route path="/" element={<TestPageForAxios/>}/>
          </Routes>
      </Router>
      </Stack>
        </ThemeProvider>
    </Box>
  );
};

export default App;
