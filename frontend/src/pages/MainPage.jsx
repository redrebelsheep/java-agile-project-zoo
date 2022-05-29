import React from "react";
import Main from "../components/main/Main.jsx";
import Rightbar from "../components/main/Rightbar.jsx";
import { Box, Stack } from "@mui/material";
import Animal from "../models/Animal"

const animalTemp = new Animal(1, "Manfred", "Katze", "12", "gehäge", "arzt");
const animalTemp2 = new Animal(2, "Manfred", "Katze", "12", "gehäge", "arzt");
const animalArray = [animalTemp, animalTemp2]

const MainPage = () => {
  return (
    <Box bgcolor="yellow" flex={4}>
      <Stack direction="row" spacing={3} p={2} justifyContent="space-between">
        <Main animals={animalArray} />
        <Rightbar />
      </Stack>
    </Box>
  );
};

export default MainPage;
