package com.store.record_store.modules.genre.services;

import com.store.record_store.modules.genre.dto.GenreResponse;
import com.store.record_store.modules.genre.dto.GenreRequest;
import com.store.record_store.modules.genre.model.genre;
import com.store.record_store.shared.service.ABaseService;

import java.util.UUID;
public interface GenreService extends ABaseService <genre, UUID, GenreRequest, GenreResponse>{

} 