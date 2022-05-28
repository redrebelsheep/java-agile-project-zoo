import React from "react";
import Main from "../components/main/Main.jsx";
import Rightbar from "../components/main/Rightbar.jsx";
import { Box, Stack } from "@mui/material";

const MainPage = () => {
  return (
    <Box bgcolor="yellow" flex={4}>
      <Stack direction="row" spacing={3} p={2} justifyContent="space-between">
        <Main />
        <Rightbar />
      </Stack>
    </Box>
  );
};

export default MainPage;
