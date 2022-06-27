package com.cascado.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cascado.server.WeatherInfo;
import static com.cascado.common.MessageConstants.REGEX;

public class Panel extends JPanel {

    private JButton enterCityButton;
    private JTextField enterCityField;
    private JLabel enterCityLabel;
    private JLabel resultCity;
    private JLabel resultTemperature;
    private JLabel resultWeather;
    private String[] receivedData = new String[3];
    private String city;

    public Panel(){
        doInterface();
        setFont();
        settingPrefSize();
        settingGridLayout();
        addComponentsToPanel();
        buttonListener();

        setVisible(true);
    }
    private void buttonListener(){
        enterCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeatherInfo weatherInfo = new WeatherInfo();
                weatherInfo.setCity(getCity());
                weatherInfo.makingArrayFromJSONArray();
            }
        });
    }

    private void addComponentsToPanel() {
        this.add(enterCityLabel);
        this.add(resultCity);
        this.add(enterCityField);
        this.add(resultTemperature);
        this.add(enterCityButton);
        this.add(resultWeather);
    }

    private void settingGridLayout() {
        GridLayout gridLayout = new GridLayout(3, 2);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void settingPrefSize() {
        enterCityLabel.setPreferredSize(new Dimension(200, 50));
        enterCityField.setPreferredSize(new Dimension(200, 50));
        enterCityButton.setPreferredSize(new Dimension(200, 50));
    }

    private void doInterface() {
        enterCityButton = new JButton("Enter");
        enterCityField = new JTextField();
        enterCityLabel = new JLabel("Enter the city", SwingConstants.CENTER);

        resultCity = new JLabel("City: ");
        resultWeather = new JLabel("Weather: ");
        resultTemperature = new JLabel("Temperature: ");
    }

    private void setFont(){
        Font font = new Font("Monaco", Font.PLAIN, 20);
        resultCity.setFont(font);
        resultWeather.setFont(font);
        resultTemperature.setFont(font);
        enterCityLabel.setFont(font);
        enterCityButton.setFont(font);
        enterCityField.setFont(font);
    }

    private String sendCityName(){
        String city = enterCityField.getText();
        if (city != null || city.isBlank()) {
            return city;
        }
        return null;
    }

    public String getCity() {
        return sendCityName();
    }
}
