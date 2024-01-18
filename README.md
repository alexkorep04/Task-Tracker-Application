Приложение написано для контроля над поставленными задачами.\
Начальная страница выглядит так.
![Screenshot_5](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/5beab4c8-af8c-46b6-847c-238d24efbcd3)
Мы можем посмотреть информацию о сайте, нажав на кнопку About Website. Содержимое данной страницы.
![Screenshot_9](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/39332d24-74cf-456d-9ff7-9626e49c3120)\
Для регистрации пользователь нажимает на кнопку Register и видит форму.\
![Screenshot_6](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/c6c839ce-d2c0-4be7-a07f-843c6d87f40f)\
Он вводит свои данные, если пароль или логин слишком короткие, а также если такой почты нет, то пользователь не сможет зарегистрироваться благодаря валидации.
![Screenshot_7](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/7062ed5f-6729-490b-ab45-4b551b1057f5)\
После завершения регистрации на почту придет письмо.
![Screenshot_27](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/8e4841bc-5d19-4c6e-8635-146fd564334b)\
Чтобы войти в  аккаунт пользователь нажимает на кнопку Login стартовой страницы. Открывается окно.
![Screenshot_10](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/22cf4627-ba36-4d1e-b248-3d8a80301580)\
Если человек не помнит свой пароль, то он может его восстановить, кликнув на ссылку. Откроется форма.
![Screenshot_12](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/3d227fa2-8ed6-4c56-b8a3-6c8035984e9d)\
Если никнейм и почта существуют в БД, а также, если такая почта привязана к никнейму, то на почту придет новый случайный пароль, который пользователь может изменить.
![Screenshot_11](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/0447af63-691f-421e-88a1-719814bf7a16)\
Если человек помнит свой логин и пароль, то может войти в аккаунт. Если логин и пароль верные, то откроется основная страница профиля.
![Screenshot_13](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/1fcf44f6-3b17-4456-9b75-d1c0dd8a99d3)
На ней видны все задачи. У задач есть характеристики: название, категория(Art, Science, Music, Sport) и дедлайн. Если дедлайн прошел, то они в отдельном списке. Также возможно удалить задачу(кнопка Delete) или выполнить ее(кнопка Complete). Также возможны обновления названия, категории и дедлайна. Здесь представлена форма, которая откроется после того, как вы нажмете Update дедлайн.
![Screenshot_15](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/5fc195fc-ae9b-49e3-ab3a-3050faa2f1b0)
Чтобы добавить задачу, пользователь нажимает на кнопку Add task, после чего открывается форма.
![Screenshot_17](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/10c7d1d8-8a45-40ef-90b0-48476133a315)
После добавления задача отобразится в общем списке. Также на основной странице профиля есть кнопка Completed tasks. Нажав на нее, вы видите уже выполненные задачи и моете вернуть их обратно в категорию невыполненных.
![Screenshot_18](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/d39ff308-62d2-4522-abf5-aae404fb77b2)
Помимо этого, на главной странице есть внопка Account Information. С помощью нее вы сможете изменить данные аккаунта. Однако сначала, необходимо подтвердить пароль в целях безопасности. Так выгляди тформа подтверждения.
![Screenshot_19](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/c922303b-520d-4c3a-b313-36aad7935ab7)
Если пароль верный. то вы видите информацию об аккаунте.
![Screenshot_20](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/b655d07c-e57b-4186-85e2-5d62feab96ce)\
Вы можете сменить пароль, никнейм или почту, нажам на соответствующие кнопки. после чего откроется новое окно. Так выглядит форма смены никнейма.
![Screenshot_21](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/27853d94-d017-408b-8c16-63a0868535c5)\
Еще одна возмодность главной страницы  профиля - кнопка Statistics. Нажав на нее, вы видите страницу, на которой необходимо ввести даты, между которыми вам нужна статистика выполненных задач, а также нужную категория(также есть категория All).
![Screenshot_22](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/93376e5e-9632-4f4a-abf6-483fce97e817)\
После этого вы увидите определенную статистику.
![Screenshot_23](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/975b66f7-8bd1-4285-8c53-5594b2a92558)\
Чтобы выйти из аккаунта воспользуйтесь кнопкой Logout. \
Также существует аккаунт андминистратора, который может блокировать и разблокировать пользователей.
![Screenshot_25](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/187e1c65-f7af-4bfc-9a15-9b0ad4eef32c)\
Помимо этого реализован REST, который позволяет найти общую информацию о каждом пользователе.
![Screenshot_26](https://github.com/alexkorep04/Task-Tracker-Application/assets/142980422/eb815ebf-3c80-45f8-b26e-03b1dc51d54a)\
Технологии:
1) JDK 21;
2) Spring(Core, AOP, Boot, JPA);
3) MySQL + Hibernate;
4) Thymeleaf;

