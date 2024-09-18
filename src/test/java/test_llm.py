import base64
import requests
import streamlit as st
import openai
# OpenAI API Key
api_key = "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"

# Function to encode the image
def encode_image(image_path):
    with open(image_path, "rb") as image_file:
        return base64.b64encode(image_file.read()).decode('utf-8')

tab1, tab2, tab3 = st.tabs(["Generuj Testy", "Analiza kodu", "Generuj unit testy"])

with tab1:
    pliki = st.file_uploader("Choose files", accept_multiple_files=True)
    ilosc_testow = st.number_input("How many tests", min_value=1)
    rodzaj_testow = st.selectbox("Type of tests", ["Integration", "UI", "E2E"])
    test_scenariusza = st.text_area("Test scenario")
    # save file to folder
    if pliki:
        for uploaded_file in pliki:
            bytes_data = uploaded_file.read()
            with open(uploaded_file.name, "wb") as f:
                f.write(bytes_data)
            st.write("filename:", uploaded_file.name)


    wyslij = st.button("Send")
    if wyslij:
        with st.spinner("Generating tests ..."):
            for plik in pliki:
                image_path = plik.name
                # Getting the base64 string
                base64_image = encode_image(image_path)

                headers = {
                    "Content-Type": "application/json",
                    "Authorization": f"Bearer {api_key}"
                }

                payload = {
                    "model": "gpt-4-turbo",
                    "messages": [
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": "scrap all text from the image"

                                },
                                {
                                    "type": "image_url",
                                    "image_url": {
                                        "url": f"data:image/jpeg;base64,{base64_image}"
                                    }
                                }
                            ]
                        }
                    ],
                    "max_tokens": 2000
                }

                response = requests.post("https://api.openai.com/v1/chat/completions", headers=headers, json=payload)
                st.image(image_path, use_column_width=True)
                st.code(response.json()["choices"][0]["message"]["content"], language="java")
with tab2:
    st.text("Analiza kodu")
    kod = st.text_area("Wklej kod do sprawdzenia")

    wyslij_kod = st.button("Analiza kodu")

    if wyslij_kod:
        with st.spinner("Analiza kodu"):
            kod_gotowy = openai.ChatCompletion.create(
                model="gpt-4",
                messages=[
                    {
                        "role": "system",
                        "content": "You Java Developer assistant "
                    },
                    {
                        "role": "user",
                        "content": "Przanalizuj kod i daj feedback oraz wygeneruj nowy kod z poprawkami \n ####: " + kod
                    }
                ],
                temperature=1,
                max_tokens=2000,
                top_p=1,
                frequency_penalty=0,
                presence_penalty=0,
                api_key="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"
            )

            kodzik = kod_gotowy["choices"][0]["message"]["content"]
            stanowisko = st.code(kodzik, language="java")
with tab3:
    st.text("Generuj unit testy")
    kod = st.text_area("Wklej kod do analizy")

    wyslij_kod = st.button("Analizuj")

    if wyslij_kod:
        with st.spinner("Generowanie testów"):
            kod_gotowy = openai.ChatCompletion.create(
                model="gpt-4",
                messages=[
                    {
                        "role": "system",
                        "content": "You Java Developer assistant "
                    },
                    {
                        "role": "user",
                        "content": "Przanalizuj kod i wygeneruj testy jednostkowe dla kodu w Javie. Uzyj framework Junit Kod: \n ####: " + kod
                    }
                ],
                temperature=1,
                max_tokens=2000,
                top_p=1,
                frequency_penalty=0,
                presence_penalty=0,
                api_key="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"
            )

            kodzik = kod_gotowy["choices"][0]["message"]["content"]
            stanowisko = st.code(kodzik, language="java")