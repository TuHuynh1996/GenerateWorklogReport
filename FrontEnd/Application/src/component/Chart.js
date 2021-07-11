
import PieChartWave from '../layout/Chart/PieChartWave'
import { Row, Col, Container, Tabs, Tab, Card } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import { FaChartLine, BiTable, BsCircleFill } from 'react-icons/all'
import TableComponent from './Table'

const data = [
    {
        "tankno": "1",
        "oiltype": "レギュラー",
        "oiltypeeng": "Unleaded",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "0.0 %",
        "wtrlev": "41mm",
        "wtrvol": "198L",
        "oillev": "1,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "2",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "78.5 %",
        "wtrlev": "42mm",
        "wtrvol": "58L",
        "oillev": "1,800L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "3",
        "oiltype": "灯油",
        "oiltypeeng": "Kerosene",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "55.0 %",
        "wtrlev": "44mm",
        "wtrvol": "10L",
        "oillev": "2,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "4",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "45.0 %",
        "wtrlev": "43mm",
        "wtrvol": "150L",
        "oillev": "1,300L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "5",
        "oiltype": "軽油",
        "oiltypeeng": "Diesel",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "23.0 %",
        "wtrlev": "47mm",
        "wtrvol": "120L",
        "oillev": "2,800L",
        "dlvvol": "0L",
        "salesvol": "0L"
    },
    {
        "tankno": "6",
        "oiltype": "レギュラー",
        "oiltypeeng": "Unleaded",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "0.0 %",
        "wtrlev": "41mm",
        "wtrvol": "198L",
        "oillev": "1,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "7",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "78.5 %",
        "wtrlev": "42mm",
        "wtrvol": "58L",
        "oillev": "1,800L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "8",
        "oiltype": "灯油",
        "oiltypeeng": "Kerosene",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "55.0 %",
        "wtrlev": "44mm",
        "wtrvol": "10L",
        "oillev": "2,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "9",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "45.0 %",
        "wtrlev": "43mm",
        "wtrvol": "150L",
        "oillev": "1,300L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "10",
        "oiltype": "軽油",
        "oiltypeeng": "Diesel",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "23.0 %",
        "wtrlev": "47mm",
        "wtrvol": "120L",
        "oillev": "2,800L",
        "dlvvol": "0L",
        "salesvol": "0L"
    },
    {
        "tankno": "11",
        "oiltype": "レギュラー",
        "oiltypeeng": "Unleaded",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "0.0 %",
        "wtrlev": "41mm",
        "wtrvol": "198L",
        "oillev": "1,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "12",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "78.5 %",
        "wtrlev": "42mm",
        "wtrvol": "58L",
        "oillev": "1,800L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "13",
        "oiltype": "灯油",
        "oiltypeeng": "Kerosene",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "55.0 %",
        "wtrlev": "44mm",
        "wtrvol": "10L",
        "oillev": "2,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "14",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "45.0 %",
        "wtrlev": "43mm",
        "wtrvol": "150L",
        "oillev": "1,300L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "15",
        "oiltype": "軽油",
        "oiltypeeng": "Diesel",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "23.0 %",
        "wtrlev": "47mm",
        "wtrvol": "120L",
        "oillev": "2,800L",
        "dlvvol": "0L",
        "salesvol": "0L"
    },
    {
        "tankno": "16",
        "oiltype": "レギュラー",
        "oiltypeeng": "Unleaded",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "0.0 %",
        "wtrlev": "41mm",
        "wtrvol": "198L",
        "oillev": "1,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "17",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "3,500L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "78.5 %",
        "wtrlev": "42mm",
        "wtrvol": "58L",
        "oillev": "1,800L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "18",
        "oiltype": "灯油",
        "oiltypeeng": "Kerosene",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "55.0 %",
        "wtrlev": "44mm",
        "wtrvol": "10L",
        "oillev": "2,200L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "19",
        "oiltype": "ハイオク",
        "oiltypeeng": "Premium",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "45.0 %",
        "wtrlev": "43mm",
        "wtrvol": "150L",
        "oillev": "1,300L",
        "dlvvol": "0L",
        "salesvol": "0L",
    },
    {
        "tankno": "20",
        "oiltype": "軽油",
        "oiltypeeng": "Diesel",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "23.0 %",
        "wtrlev": "47mm",
        "wtrvol": "120L",
        "oillev": "2,800L",
        "dlvvol": "0L",
        "salesvol": "0L"
    },
    {
        "tankno": "21",
        "oiltype": "軽油",
        "oiltypeeng": "Diesel",
        "oilvol": "5,000L",
        "wrgstr": "データ更新異常",
        "wrgeng": "No data",
        "capa": "100kL",
        "oilper": "23.0 %",
        "wtrlev": "47mm",
        "wtrvol": "120L",
        "oillev": "2,800L",
        "dlvvol": "0L",
        "salesvol": "0L"
    },
]

const Chart = () => {
    const { data2, getDat2 } = useState(null);
    const [key, setKey] = useState('pie');
    useEffect(() => {
        fetchData();
    })
    const fetchData = async () => {
        const data = await fetch('https://skkmobileapi.azurewebsites.net/api/Lvdata/?skkcode=M099000001&email=n-matsu%40showa-kiki.co.jp')
        const items = await data.json();
        console.log("items", items);
    }

    const pie = () => (
        <span>Tab1 <i className=" icon-remove" /> </span>

    )

    return (
        <Tabs
            id="controlled-tab-example"
            activeKey={key}
            onSelect={(k) => setKey(k)}
        >
            <Tab eventKey="pie" title={<BsCircleFill />}>
                <Container>
                    <Row xs={1} md={1} lg={2} xl={3}>
                        {data.map((item, index) => {
                            return (
                                <Col >
                                    <div style={{ width: '350' }}>
                                        <PieChartWave key={index} ratio={parseInt(item.oilper)} item={item} color='cyan' />
                                    </div>
                                </Col>
                            )
                        })}
                    </Row>

                </Container>
            </Tab>
            <Tab eventKey="table" title={<BiTable />}>
                <div style={{marginTop: '10px'}}>
                    <TableComponent item={data} />
                </div>
            </Tab>
            <Tab eventKey="line" title={<FaChartLine />}>

            </Tab>
        </Tabs>

    )
}
export default Chart;