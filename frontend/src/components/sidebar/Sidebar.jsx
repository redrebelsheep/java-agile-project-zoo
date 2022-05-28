import {Box} from '@mui/material';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import BadgeIcon from '@mui/icons-material/Badge';
import PetsIcon from '@mui/icons-material/Pets';
import BorderAllIcon from '@mui/icons-material/BorderAll';
import StorefrontIcon from '@mui/icons-material/Storefront';
import AssessmentIcon from '@mui/icons-material/Assessment';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import ListSidebarItem from "./ListSidebarItem";

const Sidebar = () => {
    return (
        <Box flex={1} p={2} sx={{display: {xs: "none", sm: "block"}}}>
            <List>
                <ListItem disablePadding>
                    <ListSidebarItem icon={<PetsIcon/>} name={"Animals"}/>
                </ListItem>
                <ListItem disablePadding>
                    <ListSidebarItem icon={<BadgeIcon/>} name={"Employee"}/>
                </ListItem>
                <ListItem disablePadding>
                    <ListSidebarItem icon={<BorderAllIcon/>} name={"Enclosure"}/>
                </ListItem>
                <ListItem disablePadding>
                    <ListSidebarItem icon={<StorefrontIcon/>} name={"Stalls"}/>
                </ListItem>
                <ListItem disablePadding>
                    <ListSidebarItem icon={<AssessmentIcon/>} name={"Finace"}/>
                </ListItem>
                <ListItem disablePadding>
                        <ListSidebarItem icon={<DarkModeIcon/>} />
                </ListItem>

            </List>
        </Box>
    )
}

export default Sidebar