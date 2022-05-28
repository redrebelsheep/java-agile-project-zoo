import {Button} from '@mui/material';
import {theme} from './theme'
import { ThemeProvider } from '@mui/material';
import MainPage from './components/MainPage.jsx'
import Rightbar from './components/Rightbar.jsx'
import Sidebar from './components/Sidebar.jsx'
import {Box, Stack} from '@mui/material';
import Navbar from './components/Navbar.jsx';

const App = () => {
  return (
    <Box>
      {/* <ThemeProvider theme={theme}>
      <Button variant="contained" color="secondary">Contained</Button>
      </ThemeProvider> */}
      <Navbar/>
      <Stack direction="row" spacing={3} justifyContent="space-between">
      <Sidebar/>
      <MainPage/>
      <Rightbar/>
      </Stack>
    </Box>
  );
};

export default App;
