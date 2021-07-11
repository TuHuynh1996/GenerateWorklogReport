import i18n from "i18next";
import { initReactI18next } from "react-i18next";

// the translations
// (tip move them in a JSON file and import them,
// or even better, manage them via a UI: https://react.i18next.com/guides/multiple-translation-files#manage-your-translations-with-a-management-gui)
const resources = {
  en: {
    translation: {
      "Welcome to React": "Welcome to React and react-i18next",
      home: "Home",
      chart: "chart",
      English: "English",
      Japanese: "Japanese",
      language: "Language",
      "info 1": "Info 1",
      "info 2": "Info 2",
      "info 3": "Info 3",
      "info 4": "Info 4",
      "info 5": "Info 5",
      "info 6": "Info 6",
    },
  },
  ja: {
    translation: {
      "Welcome to React": "Bienvenue à React et react-i18next",
      home: "家",
      chart: "チャート",
      English: "英語",
      Japanese: "日本語",
      language: "言語",
      "info 1": "情報 1",
      "info 2": "情報 2",
      "info 3": "情報 3",
      "info 4": "情報 4",
      "info 5": "情報 5",
      "info 6": "情報 6",
    },
  },
};

const setDefaulLanguage = () => {
  const userLang = navigator.language || navigator.userLanguage;
  console.log("userLang", userLang);
  let webLanguage = "en";

  if (userLang === "userLang") {
    webLanguage = "ja";
  }

  return {
    resources,
    lng: webLanguage, // language to use, more information here: https://www.i18next.com/overview/configuration-options#languages-namespaces-resources
    // you can use the i18n.changeLanguage function to change the language manually: https://www.i18next.com/overview/api#changelanguage
    // if you're using a language detector, do not define the lng option

    interpolation: {
      escapeValue: false, // react already safes from xss
    },
  };
};

i18n
  .use(initReactI18next) // passes i18n down to react-i18next
  .init(setDefaulLanguage());

export default i18n;
