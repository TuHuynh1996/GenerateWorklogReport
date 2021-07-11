import React, { useState } from 'react';
import { Container } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';

const rowStyles = (check) => {
    switch (check) {
        case 'Unleaded':
            return '#A9F5F2';
        case 'Premium':
            return '#00FFBF';
        case 'Kerosene':
            return '#F781F3';
        case 'Diesel':
            return '#F5DA81';
        default:
            return '#cc493f';
    }
}

const useStyles = makeStyles({
    header: {
        backgroundColor: '#CCCCCC',
    },
    border: {
        borderBottomStyle: 'solid',
        borderBottomWidth: '1px',
        borderBottomColor: 'black'
    },
    border_row: {
        borderRightStyle: 'solid',
        borderRightWidth: '1px',
        borderBottomStyle: 'solid',
        borderBottomWidth: '1px',
        borderBottomColor: 'black'
    },
});

const TableView = (props) => {
    const classes = useStyles();
    const [currentPage, setCurrentPage] = useState(0)
    const [newsPerPage, setNewPerPage] = useState(5)

    const handleChangePage = (event, newPage) => {
        setCurrentPage(newPage)
    };

    const handleChangeRowsPerPage = (event) => {
        setNewPerPage(event.target.value)
        setCurrentPage(0)
    };

    const dataTable = props.item.slice(currentPage * newsPerPage, currentPage * newsPerPage + newsPerPage)

    return (
        <Container>
            <TableContainer>
                <Table>
                    <TableHead className={classes.header}>
                        <TableRow>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '4rem', fontWeight: 'bold' }}>Tank no</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>Oil Name</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>Capacity(L)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>販売量(L)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>荷卸し(L)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>在庫量(L)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>在庫率(L)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>水位(mm)</TableCell>
                            <TableCell className={classes.border_row} align='center' style={{ minWidth: '6rem', fontWeight: 'bold' }}>水量(L)</TableCell>
                            <TableCell align="center" className={classes.border} style={{ minWidth: '8rem', fontWeight: 'bold' }}>状態</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {dataTable.map((item) => (
                            <TableRow key={item.tankno} style={{ backgroundColor: rowStyles(item.oiltypeeng) }} >
                                <TableCell className={classes.border_row} style={{ minWidth: '4rem' }}>No {item.tankno}</TableCell>
                                <TableCell className={classes.border_row} style={{ minWidth: '6rem' }}>{item.oiltype}</TableCell>
                                <TableCell className={classes.border_row} align='right' style={{ minWidth: '6rem' }}>{item.capa}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.salesvol}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.dlvvol}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.oilvol}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.oilper}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.wtrlev}</TableCell>
                                <TableCell className={classes.border_row} align="right" style={{ minWidth: '6rem' }}>{item.wtrvol}</TableCell>
                                <TableCell className={classes.border} style={{ minWidth: '8rem' }}>{item.wrgstr}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[5, 7, 8]}
                component="div"
                count={props.item.length}
                rowsPerPage={newsPerPage}
                page={currentPage}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
                labelRowsPerPage={'Change page'}
            />
        </Container>
    );
}

export default TableView;