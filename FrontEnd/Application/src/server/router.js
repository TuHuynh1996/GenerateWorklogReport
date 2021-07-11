
import About from '../component/About'
import Shop from '../component/Shop'
import Chart from '../component/Chart'

const dashboardRoutes = [
    {
      path: "/about",
      name: "about",
      component: About,
      layout: "/user",
    },
    {

        path: "/shop",
        name: "shop",
        component: Shop,
        layout: "/user",
      },
      {

        path: "/chart",
        name: "chart",
        component: Chart,
        layout: "/user",
      },
  ];
  
  export default dashboardRoutes;