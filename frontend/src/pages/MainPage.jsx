import React from "react";
import Main from "../components/main/Main.jsx";
import Rightbar from "../components/main/Rightbar.jsx";
import { Box, Stack } from "@mui/material";

const animalTempArray = [
    {
        "id": 1,
        "species": "Eisbär",
        "name": "manfred",
        "subsistenceCosts": 22.22,
        "enclosure": null,
        "vet": 1
    },
    {
        "id": 2,
        "species": "Eisbär",
        "name": "manfredleer",
        "subsistenceCosts": 22.22,
        "enclosure": null,
        "vet": null
    },
    {
        "id": 3,
        "species": "Eisbär",
        "name": "Paul",
        "subsistenceCosts": 22.22,
        "enclosure": 1,
        "vet": 1
    },
]
const MainPage = () => {
  return (
    <Box bgcolor="yellow" flex={4}>
      <Stack direction="row" spacing={3} p={2} justifyContent="space-between">
        <Main animals={animalTempArray} />
        <Rightbar />
      </Stack>
    </Box>
  );
};

export default MainPage;
