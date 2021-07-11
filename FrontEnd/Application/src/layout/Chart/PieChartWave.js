import { useEffect, useState } from "react";
import { Row, Col, Container, Card } from "react-bootstrap";
import WaveCircle from "./PiChartWaveItems/WaveCircle";
import { useTranslation } from "react-i18next";
import styled from "styled-components";

const PieChartWave = (props) => {
  const { t, i18n } = useTranslation();
  const PieChartWaveContainer = styled(Container)`
    border-radius: 10px;
    background: rgb(255, 255, 255);
    border: 1px solid rgb(157, 157, 157);
    box-shadow: 1px 1px 3px #c5c5c5;
    margin-top: 14px;
  `;

  const WaveChart = styled(Col)`
    border-right: 1px solid rgb(235, 235, 235);
  `;

  const CharInfo = styled.div`
    width: 100%;
    margin-top: 35px;
  `;

  const WaveChartNo = styled.div`
    height: 40px;
    padding: auto;
    font-weight: 600;
  `;

  const RowInfo = styled.div`
    width: 100%;
    position: relative;
    height: 28px;
    display: inline-flex;
  `;
  return (
    <PieChartWaveContainer>
      <Row xs={2}>
        <WaveChart>
          <WaveChartNo>
            <p>
              No <span> {props.item?.tankno}</span>
            </p>
          </WaveChartNo>
          <div style={{ textAlign: "center" }}>
            <WaveCircle color={props.color} ratio={props.ratio} />
          </div>
          <div style={{ textAlign: "center" }}>
            <p> {props.item?.oiltype} </p>
          </div>
        </WaveChart>
        <Col>
          <CharInfo>
            <RowInfo style={{ background: "rgb(245, 245, 245)" }}>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 1")}
              </div>{" "}
              <div> {props.item?.oilvol} </div>
            </RowInfo>
            <RowInfo>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 2")}
              </div>
              <div> {props.item?.capa}</div>
            </RowInfo>
            <RowInfo style={{ background: "rgb(245, 245, 245)" }}>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 3")}
              </div>
              <div> {props.item?.oilper}</div>
            </RowInfo>
            <RowInfo>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 4")}
              </div>
              <div> {props.item?.wtrlev}</div>
            </RowInfo>
            <RowInfo style={{ background: "rgb(245, 245, 245)" }}>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 4")}
              </div>
              <div> {props.item?.wtrvol}</div>
            </RowInfo>
            <RowInfo>
              <div
                style={{ width: "60%", textAlign: "center", fontWeight: 600 }}
              >
                {t("info 4")}
              </div>
              <div> {props.item?.oillev}</div>
            </RowInfo>
          </CharInfo>
        </Col>
      </Row>
    </PieChartWaveContainer>
  );
};
export default PieChartWave;
