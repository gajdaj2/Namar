import openai

api_key = "sk-xxxxxxxx"


def get_how_many_tests(kod):
    kod_gotowy = openai.ChatCompletion.create(
        model="gpt-4o",
        messages=[
            {
                "role": "system",
                "content": "You Java Developer assistant "
            },
            {
                "role": "user",
                "content": " How many tests you suggest to cover code bellow from good practice for cover 100% of code. "
                           "Give only number don't generate anything else. Code: " + kod
            }
        ],
        temperature=1,
        max_tokens=2000,
        top_p=1,
        frequency_penalty=0,
        presence_penalty=0,
        api_key="sk-xxxxxx"
    )

    kodzik = kod_gotowy["choices"][0]["message"]["content"]
    return kodzik


def get_code_feedback(kod, ile_testow):
    kod_gotowy = openai.ChatCompletion.create(
        model="gpt-4o",
        messages=[
            {
                "role": "system",
                "content": "You Java Developer assistant "
            },
            {
                "role": "user",
                "content": "Generate "+ile_testow+" tests in file with unit tests in testng, "
                                                  "don't use mockito for this code with all needed imports. "
                                                  "Don't generate anything else only test no explanation and etc."
                                                  "Import all needed library. Code must be compiled \n #### \n Code: " + kod
            }
        ],
        temperature=1,
        max_tokens=2000,
        top_p=1,
        frequency_penalty=0,
        presence_penalty=0,
        api_key="sk-xxxxxxxx"
    )

    kodzik = kod_gotowy["choices"][0]["message"]["content"]
    return kodzik


if __name__ == '__main__':
    filePath = './src/main/java/builder/MessageBuilder.java'
    file = open(filePath)
    kod = file.read()
    ile_testow = get_how_many_tests(kod)
    print(ile_testow)
    linie = get_code_feedback(kod, ile_testow).splitlines()
    bez_linii = linie[1:-1]
    wynik = "\n".join(bez_linii)
    file = open("./src/test/java/builder/MessageBuilderTest.java", "w")
    file.writelines(wynik)
    file.flush()
