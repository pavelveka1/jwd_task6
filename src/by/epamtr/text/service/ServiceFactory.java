package by.epamtr.text.service;

import by.epamtr.text.service.impl.TextServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final TextService textService = new TextServiceImpl();

	private ServiceFactory() {
	}

	public TextService getTextService() {

		return textService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
