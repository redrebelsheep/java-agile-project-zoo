import React from 'react'
import {Box, tableCellClasses} from '@mui/material';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

const Main = ({animals}) => {
  return (
      <TableContainer component={Paper}>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
                  <TableRow>
                      <TableCell>Name </TableCell>
                      <TableCell align="center">Species</TableCell>
                      <TableCell align="center">Costs</TableCell>
                      <TableCell align="center">Enclosure</TableCell>
                      <TableCell align="center">vet</TableCell>
                  </TableRow>
              </TableHead>
              <TableBody>
                  {animals.map((animal) => (
                      <TableRow
                          key={animal.name}
                          sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                      >
                          <TableCell component="th" scope="row">
                              {animal.name}</TableCell>
                          <TableCell align="center">{animal.species}</TableCell>
                          <TableCell align="center">{animal.subsistenceCosts}</TableCell>
                          <TableCell align="center">{animal.enclosure}</TableCell>
                          <TableCell align="center">{animal.vet}</TableCell>
                      </TableRow>
                  ))}
              </TableBody>
          </Table>
      </TableContainer>
  )
}

export default Main