import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import PetsIcon from "@mui/icons-material/Pets";
import ListItemText from "@mui/material/ListItemText";
import Switch from "@mui/material/Switch";

const ListSidebarItem = ({icon, name ="leer"}) => {
    return (
        <ListItemButton>
            <ListItemIcon>
              {icon}
            </ListItemIcon>
          {name === "leer" ?  <Switch defaultChecked size="small"/> :  <ListItemText primary={name}/>}
        </ListItemButton>
    )
}

export default ListSidebarItem