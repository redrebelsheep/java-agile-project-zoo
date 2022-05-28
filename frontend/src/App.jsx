import {Button} from '@mui/material';
import {theme} from './theme'
import { ThemeProvider } from '@mui/material';
import MainPage from './pages/MainPage.jsx'
import Sidebar from './components/sidebar/Sidebar.jsx'
import {Box, Stack} from '@mui/material';
import Navbar from './components/navbar/Navbar.jsx';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom"

const App = () => {
  return (
    <Box>
      {/* <ThemeProvider theme={theme}>
      <Button variant="contained" color="secondary">Contained</Button>
      </ThemeProvider> */}
      <Navbar/>
      <Stack direction="row" spacing={3} justifyContent="space-between">
      <Sidebar/>
      <Router>
          <Routes>
              <Route path="/" />
              <Route path="/main" element={<MainPage/>}/>
          </Routes>
      </Router>
      </Stack> 
    </Box>
  );
};

export default App;
