import Wave from "react-wavify";
import { useEffect, useState } from "react";
import styled from "styled-components";

const WaveCircle = (props) => {
  const [waveHeight, setWaveHeight] = useState(0);
  const [amplitude, setAmplitude] = useState(12);
  useEffect(() => {
    if (props.ratio !== 0) {
      if (props.ratio === 100) {
        setWaveHeight(0);
        setAmplitude(0);
      } else {
        const circleHeight =
          document.getElementsByClassName("wave-circle")[0].clientHeight;
        setWaveHeight(((100 - props.ratio) * circleHeight) / 100);
      }
    } else {
      setWaveHeight(300);
    }
  });

  const WaveCircleContent = styled.div`
    height: 8rem;
    width: 8rem;
    margin: auto;
    background-color: rgb(243, 243, 243);
    /* border: 1px solid rgb(179, 172, 172); */
    box-shadow: 0px 0px 1.5px #6d6d6d;
    border-radius: 100%;
    box-sizing: border-box;
    display: inline-block;
    text-align: center;
    position: relative;
    overflow: hidden;
  `;
  const WaveContent = styled.div`
    position: absolute;
    bottom: 0px;
    opacity: 0.5;
    height: 100%;
  `;

  return (
    <WaveCircleContent className="wave-circle">
      <WaveContent>
        <Wave
          style={{ height: "100%" }}
          fill={props.color ? props.color : "blue"}
          paused={false}
          options={{
            height: waveHeight,
            amplitude: amplitude,
            speed: 0.25,
            points: 3,
          }}
        />
      </WaveContent>
      <WaveContent>
        <Wave
          style={{ height: "101%" }}
          fill="url(#gradient)"
          paused={false}
          options={{
            height: waveHeight,
            amplitude: amplitude / 2,
            speed: 0.45,
            points: 4,
          }}
        >
          <defs>
            <linearGradient id="gradient" gradientTransform="rotate(90)">
              <stop
                offset="20%"
                stopColor={props.color ? props.color : "blue"}
              />
              <stop offset="100%" stopColor="#000000" />
            </linearGradient>
          </defs>
        </Wave>
      </WaveContent>
    </WaveCircleContent>
  );
};
export default WaveCircle;
