import Rightbar from "../components/main/Rightbar.jsx";
import { Box, Stack } from "@mui/material";
import AxiosTest from "../components/testComponents/Axiostest";

const TestPageForAxios = () => {
    return (
        <Box bgcolor="yellow" flex={4}>
            <Stack direction="row" spacing={3} p={2} justifyContent="space-between">
                <AxiosTest/>
                <Rightbar />
            </Stack>
        </Box>
    );
};

export default TestPageForAxios;
