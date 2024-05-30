# System Zarządzania Zadaniami w Zespole

## Opis Projektu
System Zarządzania Zadaniami w Zespole to aplikacja do zarządzania zadaniami w zespole, umożliwiająca tworzenie, przypisywanie, monitorowanie i zakończenie zadań. Aplikacja posiada interfejs graficzny, który pozwala użytkownikom na łatwe zarządzanie zadaniami.

## Funkcjonalności
- Dodawanie nowych zadań z tytułem i opisem.
- Przypisywanie zadań do członków zespołu.
- Monitorowanie statusu zadań (`Nowe`, `W trakcie`, `Zakończone`).
- Wyświetlanie raportu zawierającego wszystkie zadania z podziałem na kategorie.
- Śledzenie czasu pracy nad zadaniem od momentu przypisania do zakończenia.

## Technologie
- Java
- Swing (do interfejsu graficznego)

## Instalacja i Uruchomienie
1. Sklonuj repozytorium.
2. Skompiluj projekt.
3. Uruchom plik Main.java

## Użytkowanie

- Dodawanie nowego zadania
Wybierz Zadania > Dodaj nowe zadanie z menu.
Wprowadź tytuł i opis zadania.
Kliknij OK, aby dodać zadanie do listy zadań Nowe.
- Przypisywanie zadania do członka zespołu
Wybierz Zadania > Przypisz zadanie z menu.
Wybierz zadanie z listy zadań Nowe.
Wybierz członka zespołu, do którego chcesz przypisać zadanie.
Zadanie zostanie przeniesione do listy zadań W trakcie, a czas pracy nad zadaniem zacznie być śledzony.
- Zakończenie zadania
Wybierz zadanie z listy zadań W trakcie.
Kliknij przycisk Zakończ zadanie.
Zadanie zostanie przeniesione do listy zadań Zakończone, a czas pracy nad zadaniem zostanie zatrzymany.
- Wyświetlanie raportu
Wybierz Raport > Raport z menu.
Wyświetli się okno z raportem zawierającym wszystkie zadania z podziałem na kategorie (Nowe, W trakcie, Zakończone).

